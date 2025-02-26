package demo;

import com.intellij.coverage.CoverageDataManager;
import com.intellij.coverage.CoverageSuitesBundle;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiIfStatement;
import org.jetbrains.annotations.NotNull;

public class ShowBranchCoverageAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor == null) return;

        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        if (psiFile == null) return;

        // Get current coverage data
        CoverageDataManager coverageDataManager = CoverageDataManager.getInstance(project);
        CoverageSuitesBundle currentSuite = coverageDataManager.getCurrentSuitesBundle();

        if (currentSuite == null) {
            Messages.showInfoMessage("Please run tests with coverage first.", "No Coverage Data");
            return;
        }

        // Analyze branch coverage for the current file
        analyzeBranchCoverage(project, psiFile, editor, currentSuite);
    }

    private void analyzeBranchCoverage(Project project, PsiFile psiFile, Editor editor, CoverageSuitesBundle suite) {
        com.intellij.coverage.CoverageEngine engine = suite.getCoverageEngine();
        com.intellij.rt.coverage.data.ProjectData coverageData = suite.getCoverageData();
        if (coverageData == null) {
            Messages.showInfoMessage("No coverage data available.", "Error");
            return;
        }

        // Find all 'if' statements in the file and check coverage
        psiFile.accept(new JavaRecursiveElementVisitor() {
            @Override
            public void visitIfStatement(PsiIfStatement ifStatement) {
                int lineNumber = editor.getDocument().getLineNumber(ifStatement.getTextOffset()) + 1;
//                com.intellij.rt.coverage.data.LineData lineData = coverageData.getLineData(lineNumber);

//                if (lineData != null) {
//                    int hits = lineData.getHits();
//                    String branchMessage = hits > 0
//                            ? "Branch covered (Hits: " + hits + ")"
//                            : "Branch not covered";
//
//                    // Display coverage info in a dialog
//                    Messages.showInfoMessage(
//                            "Line " + lineNumber + ": " + branchMessage + "\n" +
//                                    "Condition: " + (ifStatement.getCondition() != null ? ifStatement.getCondition().getText() : "N/A"),
//                            "Branch Coverage Info"
//                    );
//                }
                super.visitIfStatement(ifStatement);
            }
        });
    }
}
