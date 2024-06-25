package com.hanna.mkodo.test.presenter.routes

sealed class NavDestinations{
    abstract fun getNavRoute(): String
}

data object DrawListDestination : NavDestinations() {
    override fun getNavRoute(): String  = "DrawList"
}

data object DrawDetailDestination : NavDestinations() {
    override fun getNavRoute(): String  = "DrawDetails/{drawId}"

    fun createNavWithArgs(id: String) = "DrawDetails/$id"
}