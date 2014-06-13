/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.provider;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.jres.model.chouse.AddIndexField;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author liaogc
 *
 */
public class IndexFieldAddColumnLabelProvider  extends EObjectColumnLabelProvider {

	/**
	 */
	public IndexFieldAddColumnLabelProvider(EStructuralFeature attribute) {
		super(attribute);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if(element instanceof AddIndexField){
			AddIndexField addIndexField = (AddIndexField) element; 
			List<TableIndexColumn> fields = addIndexField.getIndexFields();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0, length = fields.size(); i < length; i++) {
				buffer.append(fields.get(i).getColumnName());
				if (i < length - 1)
					buffer.append(",");
			}
			return buffer.toString();
		}
		return StringUtils.EMPTY;
	}


}