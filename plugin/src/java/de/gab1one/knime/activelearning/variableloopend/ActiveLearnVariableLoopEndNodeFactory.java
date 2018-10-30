/*
 *  Copyright by Gabriel Einsdorf
 *  Email: mail@gabriel-einsdorf.de
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License, Version 3, as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 */
package de.gab1one.knime.activelearning.variableloopend;

import org.knime.core.data.StringValue;
import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentFlowVariableNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.workflow.FlowVariable;

/**
 *
 * @author <a href="mailto:gabriel.einsdorf@uni.kn">Gabriel Einsdorf</a>
 */
public class ActiveLearnVariableLoopEndNodeFactory extends NodeFactory<ActiveLearnVariableLoopEndNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ActiveLearnVariableLoopEndNodeModel createNodeModel() {
        return new ActiveLearnVariableLoopEndNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getNrNodeViews() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<ActiveLearnVariableLoopEndNodeModel> createNodeView(final int viewIndex,
            final ActiveLearnVariableLoopEndNodeModel nodeModel) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NodeDialogPane createNodeDialogPane() {
        return new ActiveLearnVariableLoopEndNodeDialogPane();
    }

    public class ActiveLearnVariableLoopEndNodeDialogPane extends DefaultNodeSettingsPane {
        private DialogComponentFlowVariableNameSelection m_flowVar;
        private SettingsModelString m_controlVarModel;

        @SuppressWarnings("unchecked")
        public ActiveLearnVariableLoopEndNodeDialogPane() {
            addDialogComponent(
                    new DialogComponentColumnNameSelection(ActiveLearnVariableLoopEndNodeModel.createColumnNameModel(),
                            "Class label column", 0, StringValue.class));
            m_controlVarModel = ActiveLearnVariableLoopEndNodeModel.createControlVarModel();
            m_flowVar = new DialogComponentFlowVariableNameSelection(m_controlVarModel, "Control Variable",
                    getAvailableFlowVariables().values(), FlowVariable.Type.INTEGER);
            addDialogComponent(m_flowVar);
        }

        @Override
        public void onOpen() {
            m_flowVar.replaceListItems(getAvailableFlowVariables().values(), m_controlVarModel.getStringValue());
        }
    }
}
