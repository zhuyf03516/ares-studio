/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.IStock3Constant;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RemovedIndex;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.util.StockUtils;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author qinyuan
 *
 */
public class RemoveIndexWizardComposite extends RemoveIndexComposite implements IWizardComposite {

	TableResourceData info ;
	EditingDomain domain ;
	/**
	 * @param parent
	 * @param editorPart
	 * @param action
	 */
	public RemoveIndexWizardComposite(Composite parent, EMFFormEditor editorPart,
			Modification action) {
		super(parent, (TableResourceData)editorPart.getInfo(), editorPart.getARESResource(), action);
		
		info = (TableResourceData)editorPart.getInfo();
		domain = editorPart.getEditingDomain();
		
	}
	
	@Override
	public Command getCommand() {
		return new RecordingCommand((TransactionalEditingDomain) domain) {
			
			@Override
			protected void doExecute() {
				
				List<TableIndex> leftedIdxs = new ArrayList<TableIndex>();
				for (TableIndex idx : info.getIndexes()) {
					boolean isRemoved = false;
					for (RemovedIndex rmidx : input) {
						if (idx.getName().equals(rmidx.getName())) {
							isRemoved = true;
							break;
						}
					}
					if (!isRemoved) {
						leftedIdxs.add(idx);
					}
				}
				info.getIndexes().clear();
				info.getIndexes().addAll(leftedIdxs);
				
				//处理修改信息
				RevisionHistory rh = CoreFactory.eINSTANCE.createRevisionHistory();
				RevisionHistoryProperty p = ChouseFactory.eINSTANCE.createRevisionHistoryProperty();
				p.setAction(getAction());
				rh.setModified(StockUtils.getModificationDescription(tableData, getAction()));
				rh.getData2().put(IStock3Constant.HISTORY_DATA2_KEY, p);
				// 2012-05-18 sundl 修改记录自动添加到第一条记录
				info.getHistories().add(0, rh);
			};
		};
	}
	
}
