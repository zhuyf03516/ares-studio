package com.hundsun.ares.studio.logic.ui.extend;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.atom.constants.IAtomConstants;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.logic.constants.ILogicResType;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.TextEMPropertyDescriptor;


public class FunctionIDExtendEditingSupport implements IExtensibleModelEditingSupport {

	@Override
	public boolean isEnable(IARESElement aresElement, EClass eClass) {
		return true;
	}

	//��Ӧ����Դ���ͣ��ö��ŷֿ����ڶ���ŷ�Χ��Դ����������жϵ�Ԫ���Ƿ�ɱ༭
	@Override
	public String getName() {
		return IAtomResType.ATOM_FUNCTION + "," + ILogicResType.LOGIC_FUNCTION;
	}

	@Override
	public String getKey() {
		return IAtomConstants.FUNCTION_ID_RANGE_KEY;
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
			objIDDesc.setDisplayName("����");
			descriptors.add(objIDDesc);
		}
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]) ;
	}

}