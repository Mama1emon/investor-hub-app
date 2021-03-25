//package com.mama1emon.api.presentation.router
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentTransaction
//import com.mama1emon.impl.R
//import com.mama1emon.impl.presentation.router.Router
//import com.mama1emon.impl.presentation.view.StockListFragment
//
///**
// * Реализация роутера для навигации между фрагментами
// *
// * @author Andrey Khokhlov on 23.03.21
// */
//class RouterImpl : Router {
//    override fun showStockList(fragmentManager: FragmentManager, addToBackStack: Boolean) {
//        showFragment(
//            fragmentManager,
//            StockListFragment.getInstance(),
//            addToBackStack
//        )
//    }
//
//    private fun showFragment(
//        fragmentManager: FragmentManager,
//        fragment: Fragment,
//        addToBackStack: Boolean
//    ) {
//        fragmentManager
//            .beginTransaction()
//            .replace(R.id.main_container, fragment)
//            .apply {
//                if (addToBackStack) {
//                    addToBackStack(null)
//                }
//            }
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//            .commit()
//    }
//}