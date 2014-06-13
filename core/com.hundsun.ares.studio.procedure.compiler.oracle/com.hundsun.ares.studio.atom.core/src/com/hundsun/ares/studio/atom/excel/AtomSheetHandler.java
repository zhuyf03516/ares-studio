package com.hundsun.ares.studio.atom.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.excel.handlers.InterfaceSheetHandler;
import com.hundsun.ares.studio.core.excel.Module;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;

public abstract class AtomSheetHandler  extends InterfaceSheetHandler{

	public static Map<String, IPropertyHandler> ATOM_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();

	/** 参数属性处理对应列表 */
	public static Map<String, IPropertyHandler> VAR_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();
	
	public static Map<String, IPropertyHandler> TEXT_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();
	
	static {
		ATOM_PROPERTY_HANDLERS.putAll(INTERFACE_PROPERTY_HANDLERS);
		ATOM_PROPERTY_HANDLERS.put("所属数据库", new EMFPropertyHandler(AtomPackage.Literals.ATOM_FUNCTION__DATABASE));
		ATOM_PROPERTY_HANDLERS.put("所连数据库", new EMFPropertyHandler(AtomPackage.Literals.ATOM_FUNCTION__DATABASE));//金融产品销售系统06香港版中格式。
		
		// 内部变量
		VAR_PROPERTY_HANDLERS.put("变量", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__FLAGS));
		VAR_PROPERTY_HANDLERS.put("变量名", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__ID));
		VAR_PROPERTY_HANDLERS.put("参数名", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__ID));
		VAR_PROPERTY_HANDLERS.put("类型", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__TYPE));
		VAR_PROPERTY_HANDLERS.put("参数类型", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__TYPE));
		VAR_PROPERTY_HANDLERS.put("说明", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__NAME));
		VAR_PROPERTY_HANDLERS.put("缺省值", NullPropertyHandler.INSTANCE);
		VAR_PROPERTY_HANDLERS.put("长度", NullPropertyHandler.INSTANCE);
		
		TEXT_PROPERTY_HANDLERS.put("业务处理流程", new EMFPropertyHandler(AtomPackage.Literals.ATOM_FUNCTION__PSEUDO_CODE));
		TEXT_PROPERTY_HANDLERS.put("出错说明", NullPropertyHandler.INSTANCE);
		TEXT_PROPERTY_HANDLERS.put("操作提示", NullPropertyHandler.INSTANCE);//金融产品销售系统06香港版中有此信息，需去除。
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.AbstractSheetHandler#startSheet(org.apache.poi.hssf.usermodel.HSSFSheet)
	 */
	@Override
	public void startSheet(HSSFSheet sheet) {
		super.startSheet(sheet);
		module = new Module();
		//中文分隔符—也支持
		String sheetName = StringUtils.replace(sheet.getSheetName(), "—", "-");
		String cName = StringUtils.removeStart(sheetName, "原子函数-");
		cName = StringUtils.removeStart(cName, "原子服务-");
		module.cName = cName;
	}

	@Override
	protected Map<String, IPropertyHandler> getPropertyHandlers() {
		if (isVarBlock()) {
			return VAR_PROPERTY_HANDLERS;
		} else if (isTextBlock()) {
			return TEXT_PROPERTY_HANDLERS;
		}
		return super.getPropertyHandlers();
	}
	
	@Override
	protected Map<String, IPropertyHandler> getDefaultPropertyHandlers() {
		return ATOM_PROPERTY_HANDLERS;
	}

	@Override
	protected EStructuralFeature getTableFeature() {
		if (isVarBlock()) {
			return AtomPackage.Literals.ATOM_FUNCTION__INTERNAL_VARIABLES;
		}
		return super.getTableFeature();
	}
	
	@Override
	protected EClass getEClass() {
		if (isVarBlock()) {
			return BizPackage.Literals.PARAMETER;
		}
		return super.getEClass();
	}
	
	protected boolean isVarBlock() {
		return StringUtils.equals(parser.getCurrentBlockTag(), "变量");
	}
	
	protected boolean isTextBlock() {
		return StringUtils.equals(parser.getCurrentBlockTag(), "业务处理流程");
	}

}
