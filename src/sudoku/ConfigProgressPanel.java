/*
 * Copyright (C) 2008-12  Bernhard Hobiger
 *
 * This file is part of HoDoKu.
 *
 * HoDoKu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HoDoKu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HoDoKu. If not, see <http://www.gnu.org/licenses/>.
 */

package sudoku;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.TreeNode;


/**
 *
 * @author  hobiwan
 */
public class ConfigProgressPanel extends javax.swing.JPanel
implements ListDragAndDropChange {
    private static final SSTSConfig[] SSTS_CONFIG = new SSTSConfig[] {
        new SSTSConfig(SolutionType.FULL_HOUSE, 100),
        new SSTSConfig(SolutionType.NAKED_SINGLE, 200),
        new SSTSConfig(SolutionType.HIDDEN_SINGLE, 300),
        new SSTSConfig(SolutionType.LOCKED_PAIR, 1000),
        new SSTSConfig(SolutionType.NAKED_PAIR, 1100),
        new SSTSConfig(SolutionType.LOCKED_CANDIDATES, 1200),
        new SSTSConfig(SolutionType.LOCKED_TRIPLE, 1300),
        new SSTSConfig(SolutionType.NAKED_TRIPLE, 1400),
        new SSTSConfig(SolutionType.NAKED_QUADRUPLE, 1500),
        new SSTSConfig(SolutionType.HIDDEN_PAIR, 1600),
        new SSTSConfig(SolutionType.X_WING, 2000),
        new SSTSConfig(SolutionType.SWORDFISH, 2100),
        new SSTSConfig(SolutionType.SIMPLE_COLORS, 2200),
        new SSTSConfig(SolutionType.MULTI_COLORS, 2300),
        new SSTSConfig(SolutionType.HIDDEN_TRIPLE, 2400),
        new SSTSConfig(SolutionType.XY_WING, 2500),
        new SSTSConfig(SolutionType.HIDDEN_QUADRUPLE, 2600)
    };
    private static final long serialVersionUID = 1L;

    private StepConfig[] steps;
    private DefaultListModel model;
    private int dropIndex = -1;
    private StepConfig dropObject;
    private Color dndColor;
    private Stroke dndStroke;
    private List<StepConfig> invalidTypes = new ArrayList<StepConfig>();
    
    private boolean listView = false; // absichtlich verkehrt, damit stepList gesetzt wird

    /** Creates new form ConfigSolverPanel */
    @SuppressWarnings({"ResultOfObjectAllocationIgnored", "unchecked"})
    public ConfigProgressPanel() {
        initComponents();
        
        Color tmpColor = UIManager.getColor("List.foreground");
        dndColor = new Color(tmpColor.getRed(), tmpColor.getGreen(), tmpColor.getBlue(), 100);
        dndStroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        
        stepList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stepList.setCellRenderer(new CheckBoxRenderer());
        model = new DefaultListModel();
        stepList.setModel(model);
        new ListDragAndDrop(stepList, this, this);
        
        stepTree.setCellRenderer(new CheckRenderer());
        stepTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        stepTree.putClientProperty("JTree.lineStyle", "Angled");
        
        // Alle Werte aus den Default-Optionen setzen
        initAll(false);
        
        checkButtons(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stepTree = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        sstsButton = new javax.swing.JButton();
        mediumButton = new javax.swing.JButton();
        mediumPlusHardButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        stepScrollPane = new javax.swing.JScrollPane();
        stepList = new javax.swing.JList();
        jToolBar1 = new javax.swing.JToolBar();
        listButton = new javax.swing.JToggleButton();
        treeButton = new javax.swing.JToggleButton();

        stepTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stepTreeMousePressed(evt);
            }
        });

        upButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigProgressPanel").getString("ConfigProgressPanel.upButton.mnemonic").charAt(0));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("intl/ConfigProgressPanel"); // NOI18N
        upButton.setText(bundle.getString("ConfigProgressPanel.upButton.text")); // NOI18N
        upButton.setEnabled(false);
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        downButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigProgressPanel").getString("ConfigProgressPanel.downButton.mnemonic").charAt(0));
        downButton.setText(bundle.getString("ConfigProgressPanel.downButton.text")); // NOI18N
        downButton.setEnabled(false);
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        resetButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigProgressPanel").getString("ConfigProgressPanel.resetButton.mnemonic").charAt(0));
        resetButton.setText(bundle.getString("ConfigProgressPanel.resetButton.text")); // NOI18N
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        sstsButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigProgressPanel").getString("ConfigProgressPanel.sstsButton.mnemonic").charAt(0));
        sstsButton.setText(bundle.getString("ConfigProgressPanel.sstsButton.text")); // NOI18N
        sstsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sstsButtonActionPerformed(evt);
            }
        });

        mediumButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigProgressPanel").getString("ConfigProgressPanel.mediumButton.mnemonic").charAt(0));
        mediumButton.setText(bundle.getString("ConfigProgressPanel.mediumButton.text")); // NOI18N
        mediumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediumButtonActionPerformed(evt);
            }
        });

        mediumPlusHardButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigProgressPanel").getString("ConfigProgressPanel.mediumPlusHardButton.mnemonic").charAt(0));
        mediumPlusHardButton.setText(bundle.getString("ConfigProgressPanel.mediumPlusHardButton.text")); // NOI18N
        mediumPlusHardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediumPlusHardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(downButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(resetButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(upButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(sstsButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(113, Short.MAX_VALUE)
                        .addComponent(mediumButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(113, Short.MAX_VALUE)
                        .addComponent(mediumPlusHardButton)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {downButton, upButton});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {mediumButton, mediumPlusHardButton, resetButton, sstsButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(327, Short.MAX_VALUE)
                .addComponent(mediumPlusHardButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mediumButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upButton)
                    .addComponent(sstsButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(downButton)
                    .addComponent(resetButton)))
        );

        jPanel4.setLayout(new java.awt.BorderLayout());

        stepList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stepListMouseClicked(evt);
            }
        });
        stepList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                stepListValueChanged(evt);
            }
        });
        stepScrollPane.setViewportView(stepList);

        jPanel4.add(stepScrollPane, java.awt.BorderLayout.CENTER);

        listButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/listview16b.png"))); // NOI18N
        listButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(listButton);

        treeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/treeview16b.png"))); // NOI18N
        treeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(treeButton);

        jPanel4.add(jToolBar1, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void stepTreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stepTreeMousePressed
        TreePath path = stepTree.getPathForLocation(evt.getX(), evt.getY());
        if (path == null) {
            return;
        }
        CheckNode act = (CheckNode)path.getLastPathComponent();
        if (act != null) {
            act.toggleSelectionState();
            stepTree.repaint();
        }
    }//GEN-LAST:event_stepTreeMousePressed
        
    private void treeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treeButtonActionPerformed
        checkButtons(false);
    }//GEN-LAST:event_treeButtonActionPerformed
    
    private void listButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listButtonActionPerformed
        checkButtons(true);
    }//GEN-LAST:event_listButtonActionPerformed
    
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        initAll(true);
    }//GEN-LAST:event_resetButtonActionPerformed
    
    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        int index = stepList.getSelectedIndex();
        if (index < steps.length - 1) {
            moveOneStep(index, true);
        }
    }//GEN-LAST:event_downButtonActionPerformed
    
    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        int index = stepList.getSelectedIndex();
        if (index > 0) {
            moveOneStep(index, false);
        }
    }//GEN-LAST:event_upButtonActionPerformed
        
    private void stepListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_stepListValueChanged
        if (evt == null || ! evt.getValueIsAdjusting()) {
            if (stepList.getSelectedValue() == null) {
                return;
            }
            // "Nach oben" und "Nach unten" Buttons anpassen
            upButton.setEnabled(true);
            downButton.setEnabled(true);
            if (stepList.getSelectedIndex() == 0) {
                upButton.setEnabled(false);
            }
            if (stepList.getSelectedIndex() >= steps.length - 1) {
                downButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_stepListValueChanged
    
    private void stepListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stepListMouseClicked
        int index = stepList.locationToIndex(evt.getPoint());
        if (index == stepList.getSelectedIndex()) {
            StepConfig conf = (StepConfig) stepList.getSelectedValue();
            conf.setEnabledProgress(!conf.isEnabledProgress());
            stepList.repaint();
        }
    }//GEN-LAST:event_stepListMouseClicked

    private void sstsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sstsButtonActionPerformed
        setSSTS();
        checkSteps();
    }//GEN-LAST:event_sstsButtonActionPerformed

    private void mediumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediumButtonActionPerformed
        setAllBelowLevel(Options.getInstance().getDifficultyLevel(DifficultyType.MEDIUM.ordinal()));
        checkSteps();
    }//GEN-LAST:event_mediumButtonActionPerformed

    private void mediumPlusHardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediumPlusHardButtonActionPerformed
        setAllBelowLevel(Options.getInstance().getDifficultyLevel(DifficultyType.HARD.ordinal()));
        checkSteps();
    }//GEN-LAST:event_mediumPlusHardButtonActionPerformed

    /**
     * Enable all techniques with {@link DifficultyLevel} &lt;= <code>level</code>.
     * @param level
     */
    private void setAllBelowLevel(DifficultyLevel level) {
        invalidTypes.clear();
        for (int i = 0; i < steps.length; i++) {
            steps[i].setEnabledProgress(false);
            if (steps[i].getLevel() <= level.getOrdinal()) {
                if (steps[i].isEnabled()) {
                    steps[i].setEnabledProgress(true);
                } else {
                    invalidTypes.add(steps[i]);
                }
            }
        }
    }

    /**
     * Sets all steps for SSTS (including the correct index). {@link SolutionTyp SolutionTypes}
     * that cannot be set, are collected in {@link #invalidTypes}.
     */
    private void setSSTS() {
        invalidTypes.clear();
        // clear everything
        for (int i = 0; i < steps.length; i++) {
            steps[i].setEnabledProgress(false);
        }
        // now set the SSTS steps
        for (int i = 0; i < SSTS_CONFIG.length; i++) {
            setOneSSTSStep(SSTS_CONFIG[i]);
        }
        // sort the steps by indexProgress
        sortSteps(steps);
    }

    /**
     * Sets one step according to the contents of <code>config</code>.
     * If the {@link SSTSConfig#progressIndex progressIndex} is used by another
     * step, the indices are swapped. If the step itself is not enabled,
     * it is added to {@link #invalidTypes}.
     * @param config
     */
    private void setOneSSTSStep(SSTSConfig config) {
        // first: find the step
        StepConfig step = null;
        StepConfig other = null;
        for (int i = 0; i < steps.length; i++) {
            if (steps[i].getType() == config.type) {
                step = steps[i];
            } else if (steps[i].getIndexProgress() == config.progressIndex) {
                other = steps[i];
            }
        }
        if (other != null) {
            // the index is already used -> swap them
            other.setIndexProgress(step.getIndexProgress());
        }
        step.setIndexProgress(config.progressIndex);
        if (! step.isEnabled()) {
            // step cant be used (not enabled)
            invalidTypes.add(step);
        } else {
            step.setEnabledProgress(true);
        }
        //System.out.println("Step: " + step + "/" + step.isEnabled() + "/" + step.isEnabledProgress() + "/" + step.getIndexProgress());
    }

    /**
     * Sort steps by indexProgress
     * @param array
     */
    private void sortSteps(StepConfig[] array) {
        Arrays.sort(array, new Comparator<StepConfig>() {

            @Override
            public int compare(StepConfig o1, StepConfig o2) {
                return o1.getIndexProgress() - o2.getIndexProgress();
            }

        });
    }

    /**
     * Checks, whether all changes are valid and resets the views
     */
    private void checkSteps() {
        if (invalidTypes.size() > 0) {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < invalidTypes.size(); i++) {
                if (i != 0) {
                    buffer.append(", ");
                    if ((i % 5) == 0) {
                        buffer.append("\r\n");
                    }
                }
                buffer.append(invalidTypes.get(i).getType().getStepName());
            }
            JOptionPane.showMessageDialog(this,
                    ResourceBundle.getBundle("intl/ConfigProgressPanel").getString("ConfigProgressPanel.techniques") +
                    buffer.toString(),
                    ResourceBundle.getBundle("intl/ConfigProgressPanel").getString("ConfigProgressPanel.error"),
                    JOptionPane.ERROR_MESSAGE);
        }
        resetView();
        if (listView == false) {
            stepTree.repaint();
        }
    }
    @SuppressWarnings("unchecked")
    private void moveOneStep(int index, boolean up) {
        //System.out.println("move one step: " + index + "/" + up);
        int toIndex = up ? index + 1 : index - 1;
        StepConfig dummy = steps[index];
        steps[index] = steps[toIndex];
        steps[toIndex] = dummy;
        int dummyIndex = steps[index].getIndexProgress();
        steps[index].setIndexProgress(steps[toIndex].getIndexProgress());
        steps[toIndex].setIndexProgress(dummyIndex);
        model.remove(index);
        model.add(toIndex, steps[toIndex]);
        stepList.setSelectedIndex(toIndex);
        stepList.ensureIndexIsVisible(toIndex);
        stepList.repaint();
    }
    
    @Override
    public void moveStep(int fromIndex, int toIndex) {
        //System.out.println("moving: " + fromIndex + "/" + toIndex);
        boolean up = fromIndex < toIndex ? true : false;
        int anz = Math.abs(fromIndex - toIndex);
        if (up) {
            anz--;
        }
        for (int i = 0; i < anz; i++) {
            moveOneStep(fromIndex, up);
            if (up) {
                fromIndex++;
            } else {
                fromIndex--;
            }
        }
    }

    @Override
    public void setDropLocation(int index, StepConfig object) {
        dropIndex = index;
        dropObject = object;
        if (index != -1) {
            if (index <= stepList.getFirstVisibleIndex() + 1) {
                stepList.ensureIndexIsVisible(index - 1);
            } else if (index >= stepList.getLastVisibleIndex() - 1) {
                stepList.ensureIndexIsVisible(index + 1);
            }
        }
    }
    
    public void okPressed() {
        // Caution: steps[] is shared by ConfigSolverPanel and ConfigFindAllStepsPanel
        // okPressed() in ConfigSolverPanel has to be called first, here only the values
        // for enabledHeuristics are set
        Options instance = Options.getInstance();
        StepConfig[] orgSteps = instance.solverSteps;
        for (int i = 0; i < steps.length; i++) {
            for (int j = 0; j < orgSteps.length; j++) {
                if (steps[i].getType() == orgSteps[j].getType() &&
                        orgSteps[i].getLevel() <= DifficultyType.UNFAIR.ordinal()) {
                    orgSteps[j].setEnabledProgress(steps[i].isEnabledProgress());
                    orgSteps[j].setIndexProgress(steps[i].getIndexProgress());
                    break;
                }
            }
        }
//        orgSteps = instance.solverStepsProgress;
//        for (int i = 0; i < steps.length; i++) {
//            for (int j = 0; j < orgSteps.length; j++) {
//                if (steps[i].getType() == orgSteps[j].getType() &&
//                        orgSteps[i].getLevel() <= DifficultyType.UNFAIR.ordinal()) {
//                    orgSteps[j].setEnabledProgress(steps[i].isEnabledProgress());
//                    orgSteps[j].setIndexProgress(steps[i].getIndexProgress());
//                    break;
//                }
//            }
//        }
        orgSteps = instance.getOrgSolverSteps();
        for (int i = 0; i < steps.length; i++) {
            for (int j = 0; j < orgSteps.length; j++) {
                if (steps[i].getType() == orgSteps[j].getType() &&
                        orgSteps[i].getLevel() <= DifficultyType.UNFAIR.ordinal()) {
                    orgSteps[j].setEnabledProgress(steps[i].isEnabledProgress());
                    orgSteps[j].setIndexProgress(steps[i].getIndexProgress());
                    break;
                }
            }
        }
        instance.solverStepsProgress = instance.copyStepConfigs(instance.getOrgSolverSteps(), false, false, false, true);
//        Options.getInstance().sortProgressSteps();
    }
    
    private void initAll(boolean setDefault) {
        // Zuerst die Daten zurücksetzen
        if (setDefault) {
            // CAUTION: Reset to default resets only enabledProgress and indexProgress
            steps = Options.getInstance().copyStepConfigs(Options.getInstance().solverStepsProgress, true, false, false, true);
            StepConfig[] orgSteps = Options.DEFAULT_SOLVER_STEPS;
            for (int i = 0; i < steps.length; i++) {
                for (int j = 0; j < orgSteps.length; j++) {
                    if (steps[i].getType() == orgSteps[j].getType()) {
                        steps[i].setEnabledProgress(orgSteps[j].isEnabledProgress());
                        steps[i].setIndexProgress(orgSteps[j].getIndexProgress());
                        break;
                    }
                }
            }
            sortSteps(steps);
//            steps = Options.getInstance().copyStepConfigs(Options.DEFAULT_SOLVER_STEPS, true, false, false, true);
        } else {
            steps = Options.getInstance().copyStepConfigs(Options.getInstance().solverStepsProgress, true, false, false, true);
        }
        
        // reload everything
        resetView();
    }

    /**
     * Rebuilds the list and the tree
     */
    @SuppressWarnings("unchecked")
    private void resetView() {
        // Liste neu laden
        model.removeAllElements();
        for (int i = 0; i < steps.length; i++) {
            if (steps[i].getLevel() > DifficultyType.UNFAIR.ordinal()) {
                // only steps with difficulty EASY - UNFAIR are allowed
                continue;
            }
            model.addElement(steps[i]);
        }
        stepList.setSelectedIndex(-1);
        stepList.ensureIndexIsVisible(0);
        stepList.repaint();

        // Baum neu laden
        buildTree();
    }
    
    public void buildTree() {
        CheckNode root = new CheckNode();
        for (int i = 0; i < steps.length; i++) {
            if (steps[i].getLevel() > DifficultyType.UNFAIR.ordinal()) {
                // only steps with difficulty EASY - UNFAIR are allowed
                continue;
            }
            @SuppressWarnings("unchecked")
            Enumeration<TreeNode> en = root.children();
            CheckNode act = null;
            while (en.hasMoreElements()) {
                act = (CheckNode)en.nextElement();
                if (act.getCategory() == steps[i].getCategory()) {
                    break;
                }
                act = null;
            }
            if (act == null) {
                // neue Kategorie
                act = new CheckNode(steps[i].getCategoryName(), true,
                        steps[i].isEnabledProgress() ? CheckNode.FULL : CheckNode.NONE,
                        null, false, true, false, steps[i].getCategory());
                root.add(act);
            }
            act.add(new CheckNode(steps[i].getType().getStepName(), false,
                    steps[i].isEnabledProgress() ? CheckNode.FULL : CheckNode.NONE,
                    steps[i], false, true, false, null));
            if (act.getSelectionState() == CheckNode.FULL && ! steps[i].isEnabledProgress()) {
                act.setSelectionState(CheckNode.HALF);
            }
            if (act.getSelectionState() == CheckNode.NONE && steps[i].isEnabledProgress()) {
                act.setSelectionState(CheckNode.HALF);
            }
        }
        DefaultTreeModel tmpModel = new DefaultTreeModel(root);
        stepTree.setModel(tmpModel);
        stepTree.setShowsRootHandles(true);
        stepTree.setRootVisible(false);
        stepTree.setRowHeight(-1);
    }
    
    private void checkButtons(boolean setList) {
        boolean changeView = false;
        if (listView != setList) {
            changeView = true;
        }
        listView = setList;
        if (listView) {
            listButton.setSelected(true);
            treeButton.setSelected(false);
            if (changeView) {
                stepScrollPane.setViewportView(stepList);
                if (stepList.getSelectedIndex() >= 0) {
                    stepListValueChanged(null);
                }
            }
            stepList.requestFocusInWindow();
        } else {
            listButton.setSelected(false);
            treeButton.setSelected(true);
            if (changeView) {
                buildTree();
                stepScrollPane.setViewportView(stepTree);
            }
            stepTree.requestFocusInWindow();
        }
    }
    
    class CheckBoxRenderer extends JCheckBox implements ListCellRenderer {
        private static final long serialVersionUID = 1L;
        private boolean isTargetCell;
        private int index;
        
        public CheckBoxRenderer() {
        }
        
        @Override
        public Component getListCellRendererComponent(JList listBox, Object obj, int index,
                boolean isSelected, boolean hasFocus) {
            if (isSelected) {
                Color bg = UIManager.getColor("List.selectionBackground");
                if (bg == null) {
                    bg = UIManager.getColor("List[Selected].textBackground");
                }
                Color fg = UIManager.getColor("List.selectionForeground");
                if (fg == null) {
                    fg = UIManager.getColor("List[Selected].textForeground");
                }
                setBackground(bg);
                setForeground(fg);
//                System.out.println("SBG: " + bg);
//                System.out.println("SFG: " + fg);
                setOpaque(true);
            } else {
                setBackground(UIManager.getColor("List.background"));
                setForeground(UIManager.getColor("List.foreground"));
//                System.out.println("BG: " + UIManager.getColor("List.background"));
//                System.out.println("FG: " + UIManager.getColor("List.foreground"));
                setOpaque(false);
            }
            setText(((StepConfig)obj).toString());
            setSelected(((StepConfig)obj).isEnabledProgress());

            isTargetCell = false;
            this.index = index;
            if (index == dropIndex) {
                isTargetCell = true;
            }
            return this;
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            if (isTargetCell) {
                Insets insets = getInsets();
                g2.setColor(dndColor);
                g2.setStroke(dndStroke);
                //g2.drawLine(insets.left, 1, insets.right, 1);
                g2.drawLine(insets.left - 2, 0, insets.left - 2, 3);
                g2.drawLine(insets.left - 1, 2, getSize().width, 2);
            }
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToggleButton listButton;
    private javax.swing.JButton mediumButton;
    private javax.swing.JButton mediumPlusHardButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton sstsButton;
    private javax.swing.JList stepList;
    private javax.swing.JScrollPane stepScrollPane;
    private javax.swing.JTree stepTree;
    private javax.swing.JToggleButton treeButton;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
    
static class SSTSConfig {
    SolutionType type;
    int progressIndex;

    SSTSConfig(SolutionType type, int progressIndex) {
        this.type = type;
        this.progressIndex = progressIndex;
    }
}
}
