package com.github.shiraji.showissue.models

import com.github.shiraji.showissue.configs.ShowIssueConfig
import com.github.shiraji.showissue.doesBranchHasIssueId
import com.github.shiraji.showissue.getIssueIdFromBranchName
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import git4idea.GitLocalBranch
import git4idea.GitUtil
import org.jetbrains.plugins.github.util.GithubUrlUtil

class ShowIssueModel(e: AnActionEvent) {
    private val project: Project? = e.getData(CommonDataKeys.PROJECT)

    fun isEnable(): Boolean {
        if (project == null || project.isDisposed) {
            return false
        }

        //        ShowIssueConfig.clearIssuePath(project)

        val currentBranch = getCurrentBranch() ?: return false
        return currentBranch.doesBranchHasIssueId()
    }

    fun getIssueIdFromBranch(): Int? {
        if (!isEnable()) return null

        val currentBranch = getCurrentBranch() ?: return null
        return currentBranch.getIssueIdFromBranchName()
    }

    fun getIssueUrl(): String? {
        project ?: return null
        if (ShowIssueConfig.hasIssuePath(project)) {
            return ShowIssueConfig.getIssuePath(project)
        } else {
            return getDefaultIssueUrl()
        }
    }

    fun saveIssueUrl(issueUrl: String) {
        project ?: return
        ShowIssueConfig.setIssuePath(project, issueUrl)
    }

    private fun getDefaultIssueUrl(): String? {
        project ?: return null

        val remotes = GitUtil.getRepositoryManager(project).repositories.first().remotes
        val remoteUrl = remotes.single { it.name == "upstream" }.firstUrl ?: remotes.single { it.name == "origin" }.firstUrl ?: return null
        return "${GithubUrlUtil.makeGithubRepoUrlFromRemoteUrl(remoteUrl, "https://" + GithubUrlUtil.getHostFromUrl(remoteUrl))}/issues/"
    }

    private fun getCurrentBranch(): GitLocalBranch? {
        project ?: return null
        return GitUtil.getRepositoryManager(project).repositories.first().currentBranch
    }
}