package com.github.shiraji.showissue.actions

import com.github.shiraji.showissue.models.ShowIssueModel
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ShowIssueAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent?) {
        e ?: return
        val model = ShowIssueModel(e)
        val issueId = model.getIssueIdFromBranch() ?: return
        val issueUrl = model.getIssueUrl() ?: return
        BrowserUtil.open("$issueUrl$issueId")
        model.saveIssueUrl(issueUrl)
    }

    override fun update(e: AnActionEvent?) {
        e ?: return
        super.update(e)
        val model = ShowIssueModel(e)
        e.presentation.isVisible = model.isVisible()
        e.presentation.isEnabled = model.isEnable()
    }
}