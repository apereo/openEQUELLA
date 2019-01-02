/*
 * Copyright 2019 Apereo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.client.gui.popup;

import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 * @author Nicholas Read
 */
public class TablePopupListener extends AbstractPopupListener
{
	private final JTable table;

	public TablePopupListener(JTable table, Action... actions)
	{
		super(actions);
		this.table = table;
	}

	public TablePopupListener(JTable table, List<? extends Action> actions)
	{
		super(actions);
		this.table = table;
	}

	public TablePopupListener(JTable table, JPopupMenu menu)
	{
		super(menu);
		this.table = table;
	}

	@Override
	public void selectItemUnderMouse(MouseEvent e)
	{
		table.editingCanceled(null);
		int row = table.rowAtPoint(e.getPoint());
		table.getSelectionModel().setSelectionInterval(row, row);
	}
}
