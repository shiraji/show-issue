package com.github.shiraji.showissue.configs

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project

class ShowIssueConfig {
    companion object {
        private val ISSUE_PATH_KEY: String = "com.github.shiraji.showissue.configs.ShowIssueConfig.Companion.ISSUE_PATH_KEY"
        private val BRANCH_REGEX_KEY: String = "com.github.shiraji.showissue.configs.ShowIssueConfig.Companion.BRANCH_REGRE_KEY"

        fun setIssuePath(project: Project, path: String)
                = PropertiesComponent.getInstance(project).setValue(ISSUE_PATH_KEY, path)

        fun getIssuePath(project: Project)
                = PropertiesComponent.getInstance(project).getValue(ISSUE_PATH_KEY)

        fun hasIssuePath(project: Project)
                = PropertiesComponent.getInstance(project).isValueSet(ISSUE_PATH_KEY)

        fun clearIssuePath(project: Project)
                = PropertiesComponent.getInstance(project).unsetValue(ISSUE_PATH_KEY)

        fun setBranchRegEx(project: Project, regex: String)
                = PropertiesComponent.getInstance(project).setValue(BRANCH_REGEX_KEY, regex)

        fun getBranchRegEx(project: Project)
                = PropertiesComponent.getInstance(project).getValue(BRANCH_REGEX_KEY)

        fun hasBranchRegEx(project: Project)
                = PropertiesComponent.getInstance(project).isValueSet(BRANCH_REGEX_KEY)
    }
}