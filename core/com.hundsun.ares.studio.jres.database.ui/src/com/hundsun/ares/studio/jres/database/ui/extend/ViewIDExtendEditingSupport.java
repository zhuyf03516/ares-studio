package com.hundsun.ares.studio.jres.database.ui.extend;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.TextEMPropertyDescriptor;

public class ViewIDExtendEditingSupport implements IExtensibleModelEditingSupport {

	@Override
	public boolean isEnable(IARESElement aresElement, EClass eClass) {
		return true;
	}

	//对应的资源类型，用逗号分开，在对象号范围资源中用这个来判断单元格是否可编辑
	@Override
	public String getName() {
		return IDatabaseResType.View;
	}

	@Override
	public String getKey() {
		return IDBConstant.VIEW_ID_RANGE_KEY;
	}

	@Override
	public EObject createMapValueObject() {
		return MetadataFactory.eINSTANCE.createIDRange();
	}

	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(
			IARESElement aresElement, EClass eClass) {
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();
		{
			TextEMPropertyDescriptor objIDDesc = new TextEMPropertyDescriptor(MetadataPackage.Literals.ID_RANGE__RANGE);
			objIDDesc.setDisplayName("视图");
			descriptors.add(objIDDesc);
		}
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]) ;
	}

}
