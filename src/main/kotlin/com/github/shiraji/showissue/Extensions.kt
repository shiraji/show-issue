package com.github.shiraji.showissue

import git4idea.GitLocalBranch

// TODO make reg customizable.

fun GitLocalBranch.getIssueIdFromBranchName() = "(\\d\\d*)\\D.*".toRegex().find(this.name)!!.groups[1]!!.value.toInt()

fun GitLocalBranch.doesBranchHasIssueId() = "(\\d\\d*)\\D.*".toRegex().matches(this.name)