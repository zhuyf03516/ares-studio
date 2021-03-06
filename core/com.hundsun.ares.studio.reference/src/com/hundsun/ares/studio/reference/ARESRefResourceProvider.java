/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResourceDelta;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.core.validate.IRefResourceProvider;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.model.reference.RelationInfo;

/**
 * @author gongyf
 *
 */
public class ARESRefResourceProvider implements IRefResourceProvider {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.validate.IRefResourceProvider#getRefResources(com.hundsun.ares.studio.core.IARESResource, org.eclipse.core.resources.IResourceDelta, java.util.Map)
	 */
	@Override
	public Collection<IARESResource> getRefResources(IARESResource res,
			IResourceDelta delta, Map<String, IAresContext> contexts) {
		Set<IARESResource> result = new HashSet<IARESResource>();
		// 查找是根据当前环境下进行的，无法得知之前的引用关系
		List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(res);
		for (ReferenceInfo info : infoList) {

			//目前不需要处理有命名空间的情况
		/*	List<RelationInfo> rels = ReferenceManager.getInstance().getRelationInfoByTarget(info.getRefType(), info.getRefName(), info.getRefNamespace(), res.getARESProject());
			for (RelationInfo rel : rels) {
				result.add(rel.getHostResource());
			}*/
			
			// 需要处理空白命名空间的情况
			List<RelationInfo>  rels = ReferenceManager.getInstance().getRelationInfoByTarget(info.getRefType(), info.getRefName(), res.getARESProject());
			for (RelationInfo rel : rels) {
				result.add(rel.getHostResource());
			}
		}
		
		return result;
	}

}
