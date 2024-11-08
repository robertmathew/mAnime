package org.robert.project.ui

sealed class Screen(val route: String) {
    object ListScreen: Screen("list_screen")
    object DetailScreen: Screen("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args -> append("/$args") }
        }
    }

    fun withIdArgs(id: Int?): String {
        return buildString {
            append(route)
            append("?id=$id")
        }
    }
}