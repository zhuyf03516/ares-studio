/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.model.metadata.decrypt.provider;

import com.hundsun.ares.studio.jres.model.metadata.decrypt.util.DecryptAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DecryptItemProviderAdapterFactory extends DecryptAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecryptItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeMetadataItemItemProvider deMetadataItemItemProvider;

	/**
	 * This creates an adapter for a {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeMetadataItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeMetadataItemAdapter() {
		if (deMetadataItemItemProvider == null) {
			deMetadataItemItemProvider = new DeMetadataItemItemProvider(this);
		}

		return deMetadataItemItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeTypeDefaultValue} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeTypeDefaultValueItemProvider deTypeDefaultValueItemProvider;

	/**
	 * This creates an adapter for a {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeTypeDefaultValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeTypeDefaultValueAdapter() {
		if (deTypeDefaultValueItemProvider == null) {
			deTypeDefaultValueItemProvider = new DeTypeDefaultValueItemProvider(this);
		}

		return deTypeDefaultValueItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeStandardDataTypeItemProvider deStandardDataTypeItemProvider;

	/**
	 * This creates an adapter for a {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardDataType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeStandardDataTypeAdapter() {
		if (deStandardDataTypeItemProvider == null) {
			deStandardDataTypeItemProvider = new DeStandardDataTypeItemProvider(this);
		}

		return deStandardDataTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeBusinessDataTypeItemProvider deBusinessDataTypeItemProvider;

	/**
	 * This creates an adapter for a {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeBusinessDataType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeBusinessDataTypeAdapter() {
		if (deBusinessDataTypeItemProvider == null) {
			deBusinessDataTypeItemProvider = new DeBusinessDataTypeItemProvider(this);
		}

		return deBusinessDataTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeStandardFieldItemProvider deStandardFieldItemProvider;

	/**
	 * This creates an adapter for a {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeStandardFieldAdapter() {
		if (deStandardFieldItemProvider == null) {
			deStandardFieldItemProvider = new DeStandardFieldItemProvider(this);
		}

		return deStandardFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeDictionaryTypeItemProvider deDictionaryTypeItemProvider;

	/**
	 * This creates an adapter for a {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeDictionaryTypeAdapter() {
		if (deDictionaryTypeItemProvider == null) {
			deDictionaryTypeItemProvider = new DeDictionaryTypeItemProvider(this);
		}

		return deDictionaryTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeDictionaryItemItemProvider deDictionaryItemItemProvider;

	/**
	 * This creates an adapter for a {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeDictionaryItemAdapter() {
		if (deDictionaryItemItemProvider == null) {
			deDictionaryItemItemProvider = new DeDictionaryItemItemProvider(this);
		}

		return deDictionaryItemItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeConstantItem} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeConstantItemItemProvider deConstantItemItemProvider;

	/**
	 * This creates an adapter for a {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeConstantItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeConstantItemAdapter() {
		if (deConstantItemItemProvider == null) {
			deConstantItemItemProvider = new DeConstantItemItemProvider(this);
		}

		return deConstantItemItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeErrorNoItem} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeErrorNoItemItemProvider deErrorNoItemItemProvider;

	/**
	 * This creates an adapter for a {@link com.hundsun.ares.studio.jres.model.metadata.decrypt.DeErrorNoItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDeErrorNoItemAdapter() {
		if (deErrorNoItemItemProvider == null) {
			deErrorNoItemItemProvider = new DeErrorNoItemItemProvider(this);
		}

		return deErrorNoItemItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (deMetadataItemItemProvider != null) deMetadataItemItemProvider.dispose();
		if (deTypeDefaultValueItemProvider != null) deTypeDefaultValueItemProvider.dispose();
		if (deStandardDataTypeItemProvider != null) deStandardDataTypeItemProvider.dispose();
		if (deBusinessDataTypeItemProvider != null) deBusinessDataTypeItemProvider.dispose();
		if (deStandardFieldItemProvider != null) deStandardFieldItemProvider.dispose();
		if (deDictionaryTypeItemProvider != null) deDictionaryTypeItemProvider.dispose();
		if (deDictionaryItemItemProvider != null) deDictionaryItemItemProvider.dispose();
		if (deConstantItemItemProvider != null) deConstantItemItemProvider.dispose();
		if (deErrorNoItemItemProvider != null) deErrorNoItemItemProvider.dispose();
	}

}
