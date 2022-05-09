package com.yxhuang.androidtest.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.yxhuang.androidtest.adapters.ImageAdapter
import javax.inject.Inject

/**
 * Created by yxhuang
 * Date: 2022/5/8
 * Description:
 */
class ShoppingFragmentFactory @Inject constructor(
        private val imageAdapter: ImageAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className){
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            else ->super.instantiate(classLoader, className)
        }
    }

}