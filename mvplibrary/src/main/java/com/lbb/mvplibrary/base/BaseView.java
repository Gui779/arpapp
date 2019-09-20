package com.lbb.mvplibrary.base;


import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */


public interface BaseView {

    <T> LifecycleTransformer<T> bindToLifecycle();



}
