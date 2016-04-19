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

        // TODO : Get URL from origin or upstream url as a default value
        BrowserUtil.open("${model.getDefaultIssueUrl()}$issueId")
    }

    override fun update(e: AnActionEvent?) {
        e ?: return
        super.update(e)
        e.presentation.isEnabledAndVisible = ShowIssueModel(e).isEnable()
    }
}