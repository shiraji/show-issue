package com.github.shiraji.showissue.models

import com.github.shiraji.showissue.doesBranchHasIssueId
import com.github.shiraji.showissue.getIssueIdFromBranchName
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import git4idea.GitLocalBranch
import git4idea.GitUtil

class ShowIssueModel(e: AnActionEvent) {
    private val project: Project? = e.getData(CommonDataKeys.PROJECT)

    fun isEnable(): Boolean {
        if (project == null || project.isDisposed) {
            return false
        }
        val currentBranch = getCurrentBranch() ?: return false
        return currentBranch.doesBranchHasIssueId()
    }

    fun getIssueIdFromBranch(): Int? {
        if (!isEnable()) return null

        val currentBranch = getCurrentBranch() ?: return null
        return currentBranch.getIssueIdFromBranchName()
    }

    private fun getCurrentBranch(): GitLocalBranch? {
        project ?: return null
        return GitUtil.getRepositoryManager(project).repositories.first().currentBranch
    }
}