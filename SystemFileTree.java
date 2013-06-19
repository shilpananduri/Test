/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
 
 
import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class SystemFileTree {

  public static void main(String[] args) {
    final Display display = new Display();
    final Shell shell = new Shell(display);

    RGB color = shell.getBackground().getRGB();
    Label separator1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
    Label locationLb = new Label(shell, SWT.NONE);
    locationLb.setText("Location:");
    Composite locationComp = new Composite(shell, SWT.EMBEDDED);
    Label separator2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
    final Composite comp = new Composite(shell, SWT.NONE);
    final Tree fileTree = new Tree(comp, SWT.SINGLE | SWT.BORDER);
    Sash sash = new Sash(comp, SWT.VERTICAL);
    Composite tableComp = new Composite(comp, SWT.EMBEDDED);
    Label separator3 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
    Composite statusComp = new Composite(shell, SWT.EMBEDDED);
    
    java.awt.Frame locationFrame = SWT_AWT.new_Frame(locationComp);
    final java.awt.TextField locationText = new java.awt.TextField();
    locationFrame.add(locationText);

    java.awt.Frame statusFrame = SWT_AWT.new_Frame(statusComp);
    statusFrame.setBackground(new java.awt.Color(color.red, color.green, color.blue));
    final java.awt.Label statusLabel = new java.awt.Label();
    statusFrame.add(statusLabel);
    statusLabel.setText("Select a file");

    sash.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event e) {
        if (e.detail == SWT.DRAG)
          return;
        GridData data = (GridData) fileTree.getLayoutData();
        Rectangle trim = fileTree.computeTrim(0, 0, 0, 0);
        data.widthHint = e.x - trim.width;
        comp.layout();
      }
    });

    File[] roots = File.listRoots();
    for (int i = 0; i < roots.length; i++) {
      File file = roots[i];
      TreeItem treeItem = new TreeItem(fileTree, SWT.NONE);
      treeItem.setText(file.getAbsolutePath());
      treeItem.setData(file);
      new TreeItem(treeItem, SWT.NONE);
    }
    fileTree.addListener(SWT.Expand, new Listener() {
      public void handleEvent(Event e) {
        TreeItem item = (TreeItem) e.item;
        if (item == null)
          return;
        if (item.getItemCount() == 1) {
          TreeItem firstItem = item.getItems()[0];
          if (firstItem.getData() != null)
            return;
          firstItem.dispose();
        } else {
          return;
        }
        File root = (File) item.getData();
        File[] files = root.listFiles();
        if (files == null)
          return;
        for (int i = 0; i < files.length; i++) {
          File file = files[i];
          if (file.isDirectory()) {
            TreeItem treeItem = new TreeItem(item, SWT.NONE);
            treeItem.setText(file.getName());
            treeItem.setData(file);
            new TreeItem(treeItem, SWT.NONE);
          }
        }
      }
    });
    fileTree.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event e) {
        TreeItem item = (TreeItem) e.item;
        if (item == null)
          return;
        final File root = (File) item.getData();
        statusLabel.setText(root.getAbsolutePath());
        locationText.setText(root.getAbsolutePath());
      }
    });

    GridLayout layout = new GridLayout(4, false);
    layout.marginWidth = layout.marginHeight = 0;
    layout.horizontalSpacing = layout.verticalSpacing = 1;
    shell.setLayout(layout);
    GridData data;
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 4;
    separator1.setLayoutData(data);
    data = new GridData();
    data.horizontalSpan = 1;
    data.horizontalIndent = 10;
    locationLb.setLayoutData(data);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 2;
    data.heightHint = locationText.getPreferredSize().height;
    locationComp.setLayoutData(data);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 1;
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 4;
    separator2.setLayoutData(data);
    data = new GridData(GridData.FILL_BOTH);
    data.horizontalSpan = 4;
    comp.setLayoutData(data);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 4;
    separator3.setLayoutData(data);
    data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 4;
    data.heightHint = statusLabel.getPreferredSize().height;
    statusComp.setLayoutData(data);

    layout = new GridLayout(3, false);
    layout.marginWidth = layout.marginHeight = 0;
    layout.horizontalSpacing = layout.verticalSpacing = 1;
    comp.setLayout(layout);
    data = new GridData(GridData.FILL_VERTICAL);
    data.widthHint = 200;
    fileTree.setLayoutData(data);
    data = new GridData(GridData.FILL_VERTICAL);
    sash.setLayoutData(data);
    data = new GridData(GridData.FILL_BOTH);
    tableComp.setLayoutData(data);

    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}