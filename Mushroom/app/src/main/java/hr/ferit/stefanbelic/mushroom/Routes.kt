package hr.ferit.stefanbelic.mushroom

sealed class Routes(val routePath: String) {
    object HOME : Routes("home")
    object IZBORNIK : Routes("izbornik")
    object OTROVNE : Routes("otrovneGljive")
    object JESTIVE : Routes("jestiveGljive")
    object ADD_MAUHROOM : Routes("ADD_MAUHROOM" + "/{mushroomType}")
}