/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.exception.MacroNotInProcBlockException;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.TableResourceUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.token.SelectInsertTableInProcBlockToken;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author zhuyf
 *
 */
public class SelectInsertTableInProcBlockMacroHandler implements
		IMacroTokenHandler {
	
	private AtomFunction atomFunction;//原子函数模型

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.SELECT_INSERT_TABLE_INPROCBLOCK_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 输入描述：
		[SELECT插入表记录][目标表][源表][默认值]
		处理流程：
		在伪代码编辑器中输入[select插入表记录][目标表][源表][默认值]，点击代码预览tab页，查看对应的真实代码。
		1.在具体的处理过程中，首先取得目标表和源表的表名，如果任一表不存在，则抛出异常“插入表记录,表不存在”，再根据目标表名得到目标表所有的字段名，如果表字段在标准字段中找不到，则抛出异常“表字段不存在”，在每个字段值插入具体的值，取值规则如下：
		首先判断在[默认值]中是否有该字段的默认值，如果有则取到该默认值，如果没有就从源表中找，如果源表中存在和该字段相同的表字段则取出源表中SELECT取得该字段的值作为插入值，如果没有就从输入输出参数和内部变量中寻找，如果存在和该字段同名的变量则将该变量的值作为插入值，如果还没有则根据字段名从标准字段列表中取的该字段的真实默认值作为插入值。
		2.该宏一般出现在过程或函数的PROC语句块中。
		3.如果未在PROC语句块中使用，则抛出异常。
		4.目标表与源表都支持跨用户插入表记录，如[SELECT插入表记录][hs_data.rl_stockholderjour][hs_asset.cl_stockholderjour]
		5.源表支持SQL，如[SELECT插入表记录][clientjour][select a.*,b.full_name from client a,clientinfo b where a.client_id=b.client_id]
		6.支持跨用户表名，如[SELECT插入表记录][his_clientjour][select a.*,b.full_name from client a,clientinfo b where a.client_id=b.client_id][clientjour]
			[SELECT插入表记录][fil_clientjour][select a.*,b.full_name from client a,clientinfo b where a.client_id=b.client_id][clientjour]
			[SELECT插入表记录][r_clientjour][select a.*,b.full_name from client a,clientinfo b where a.client_id=b.client_id][clientjour]
			[SELECT插入表记录][rl_clientjour][select a.*,b.full_name from client a,clientinfo b where a.client_id=b.client_id][clientjour]
		7.处理以下情况：
		[SELECT插入表记录][hs_his.his_demoinfojour][select a.*, 
		nvl( (select * from sysdictionary b 
         where b.dict_entry = < CNST_DICT_USER_TYPE > 
   			and b.subentry = a.user_type) ,' ') as user_type_name 
  			from hs_user.demoinfojour a][demoinfojour]
		 */
		this.atomFunction = (AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		this.addMacroNameToMacroList(token, context);
		List<ICodeToken> codes = new ArrayList<ICodeToken>();
		if (token.getParameters().length >=2) {		
			if (!isInProcBlock(context)) {//判断是否在ProcBlock块中
				throw new MacroNotInProcBlockException("[SELECT插入表记录]");
			}else {
				Map<String, String> defaultValueMap = new HashMap<String, String>();
				if(token.getParameters().length> 2){
					defaultValueMap = PseudoCodeParser.parserKeyValueWithAt(token.getParameters()[2]);//解析 默认值列表
				}
				addFieldToProc(token,context,defaultValueMap);
				addPVarList(defaultValueMap,context);//把默认值列表中的变量加到变量列表中
				codes.add(new SelectInsertTableInProcBlockToken(token, context,defaultValueMap));
			}
		} else {
			fireEventLessParameter(context);// 发送缺少参数事件
		}
		
		return codes.iterator();
	}
	/**
	 * 发送缺少参数事件
	 */
	
	private void fireEventLessParameter(Map<Object, Object> context){

		ITokenListenerManager  manager =(ITokenListenerManager) context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = String.format("宏[%s]缺少参数，必须有表名参数。", MacroConstant.SELECT_INSERT_TABLE_INPROCBLOCK_MACRONAME);
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	
	}
	
	/**
	 * 把字段估添加到变量列表中去
	 * 
	 * @param procVarList
	 * @param tableColumns
	 */
	private void addPVarList(Map<String, String> defaultValueMap,
			Map<Object, Object> context) {
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
		for(String paramKey: defaultValueMap.keySet()){
			String valueVarName =defaultValueMap.get(paramKey);
			if (valueVarName.indexOf("@") >= 0 && !AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(getAtomFunction(), valueVarName.substring(valueVarName.indexOf("@") + 1),project)) {// 如果默认参数值为变量
				String procVarName = valueVarName.substring(valueVarName.indexOf("@") + 1);
				((ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER)).addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST,procVarName);
				helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST,procVarName);
				popVarList.add(procVarName);
			}
		}
		
	
	}
	
	/**
	 * 把一些其他字段增加到proc列表中去
	 * @param macroToken
	 * @param context
	 * @param defaultValueMap
	 * @throws Exception
	 */
	private void addFieldToProc(IMacroToken macroToken,Map<Object, Object> context,Map<String, String> defaultValueMap) throws Exception{
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		Set<String> procList = (Set<String>)helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST);
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
		String targerTableName = macroToken.getParameters()[0];//目标表名
		String sourceTableName = macroToken.getParameters()[1];//源表名
		List<TableColumn>  targetColumns = TableResourceUtil.getFieldsWithoutFlag("H",targerTableName,project);//获取目标表所有列
		
		for (int i = 0; i < targetColumns.size(); i++) {

			TableColumn targerTableColumn = targetColumns.get(i);
			StandardField tableFieldSTD  = MetadataServiceProvider.getStandardFieldByName((IARESProject)context.get(IAtomEngineContextConstantMySQL.Aresproject), targerTableColumn.getFieldName());
			if (tableFieldSTD != null) {
				if (defaultValueMap.get(tableFieldSTD.getName()) == null) {//如果不在字段中默认值列表中
					
					TableColumn sourceTableColumn = null;
					// 根据名字在源表中查找列
					for (TableColumn f : TableResourceUtil.getFieldsWithoutFlag("H", sourceTableName,project)) {
						if (f.getFieldName().equals(targerTableColumn.getFieldName())) {
							sourceTableColumn = f;
							break;
						}
					}
					if (sourceTableColumn == null)// 
					 {//如果源表中不存在
						//如果在伪代码变量,proc变量输入,输出,内部变量中存在
						if (popVarList.contains(tableFieldSTD.getName())|| procList.contains(tableFieldSTD.getName())||AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(getAtomFunction(), tableFieldSTD.getName(),project)) {
							if(AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(getAtomFunction(), tableFieldSTD.getName(),project)){
								procList.add(tableFieldSTD.getName());
							}
							
						} 
					}
				}
			}
		}

		
	
	}
	
	/**
	 * 检查是否在proc语句块宏中
	 * @return
	 */
	private boolean isInProcBlock(Map<Object, Object> context){
		IDomainHandler handler = (IDomainHandler)  context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain procBlockBeginDomain =handler.getDomain(MacroConstant.PROC_BLOCK_BEGIN_MACRONAME);
		//如果前面有PRO*C语句块开始则procBlockBeginDomain不为null
		return procBlockBeginDomain!=null;
	}
	/**
	 * 返回原子函数模型
	 * @return
	 */
	private AtomFunction getAtomFunction(){
		return this.atomFunction;
		}
	
	/**
	 * 把宏名加入到宏列表中
	 */
	private void addMacroNameToMacroList(IMacroToken token,Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_DATABASE_MACRO,token.getKeyword());//添加到数据库列表中
	    helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_MACRO,token.getKeyword());//添加到proc宏列表中
	}
	

}
