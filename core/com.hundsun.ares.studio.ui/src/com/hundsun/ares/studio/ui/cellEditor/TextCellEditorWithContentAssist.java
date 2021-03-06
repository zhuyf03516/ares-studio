/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.cellEditor;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

/**
 * 带智能提示的CellEditor
 * @author sundl
 */
public abstract class TextCellEditorWithContentAssist extends TextCellEditor {
	
	private boolean popupOpen;
	
	private SimpleContentProposalAdapter adapter;
	
	public TextCellEditorWithContentAssist(Composite parent) {
		super(parent);
	}
	
	@Override
	protected Control createControl(Composite parent) {
		Control control = super.createControl(parent);
		
		adapter = new SimpleContentProposalAdapter(control, new TextContentAdapter(), getProposalProvider(), null, null);
		adapter.setPropagateKeys(true);
		
		adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		adapter.addContentProposalListener(new IContentProposalListener2(){
			public void proposalPopupClosed(ContentProposalAdapter adapter) {
				Event e = new Event();
				e.data = new Boolean(false);
				popupOpen = false;
			}
			public void proposalPopupOpened(ContentProposalAdapter adapter) {
				Event e = new Event();
				e.data = new Boolean(true);
				popupOpen = true;
			}});
		
		control.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// FIXME 这里应该从首选项中读取当前设置的文本编辑器的智能提示快捷键，或者在new SimpleContentProposalAdapter时就指定好快捷键
				if(e.character=='/' && e.stateMask==SWT.ALT){
					adapter.openProposalPopup();
				}
			}		
		});
		adapter.setPopupSize(new Point(200, 100));
		
		return control;
	}
	
	public abstract IContentProposalProvider getProposalProvider();
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object value) {
		// 防止刚激活编辑单元就弹出下拉提示框
		adapter.setEnabled(false);
		super.doSetValue(value);
		adapter.setEnabled(true);
	}
	
	@Override
	protected void focusLost() {
		if (!isPopupOpen()) {
			// Focus lost deactivates the cell editor.
			// This must not happen if focus lost was caused by activating
	        // the completion proposal popup.
			super.focusLost();
		}
	}
	
	/**
	 * 解决智能提示鼠标双击不能回填的问题。解决方法：重写focusLost()和dependsOnExternalFocusListener()方法。
	 */
	protected boolean dependsOnExternalFocusListener() {
		return false;
	}
	
	public void setContentProposalProvider(IContentProposalProvider provider) {
		this.adapter.setContentProposalProvider(provider);
	}
	
	public boolean isPopupOpen() {
		return popupOpen;
	}
}
