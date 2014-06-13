/**
 * 源程序名称：AddItemAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataViewerUtil;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.UncategorizedItemsCategoryImpl;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;

/**
 * 添加条目操作
 * @author gongyf
 *
 */
public class AddItemAction extends ColumnViewerAction {

	private EClass itemClass;
	
	/**
	 * @return the itemClass
	 */
	public EClass getItemClass() {
		return itemClass;
	}

	/**
	 * @param itemClass the itemClass to set
	 */
	public void setItemClass(EClass itemClass) {
		this.itemClass = itemClass;
	}

	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public AddItemAction(ColumnViewer viewer, EditingDomain editingDomain, EClass itemClass) {
		super(viewer, editingDomain);

		this.itemClass = itemClass;
		setText("添加条目");
		setId(IMetadataActionIDConstant.CV_ADD_ITEM);
		setToolTipText("添加条目");
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#createCommand()
	 */
	@Override
	protected Command createCommand() {
		EObject item = itemClass.getEPackage().getEFactoryInstance().create(itemClass);
		MetadataResourceData<?> owner = (MetadataResourceData<?>) getViewer().getInput();
		CompoundCommand command = new CompoundCommand(getText());
		
		command.append(AddCommand.create(getEditingDomain(), owner, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS, item));
		
		if (MetadataViewerUtil.isShowCategory(getViewer())) {
			MetadataCategory category = MetadataViewerUtil.getSelectedCategory(getViewer(), false);
			// 如果是root的分类，则不应该添加
			if (!(category instanceof UncategorizedItemsCategoryImpl)
					&& category != owner.getRoot()) {
				command.append(AddCommand.create(getEditingDomain(), category, MetadataPackage.Literals.METADATA_CATEGORY__ITEMS, item));
			}
		}

		return command.unwrap();
	}

}
