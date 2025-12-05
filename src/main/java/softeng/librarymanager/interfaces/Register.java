/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softeng.librarymanager.interfaces;

import javafx.collections.ObservableList;

/**
 *
 * @author Jakub
 */
public interface Register<T> extends ItemAcceptor<T>{
    public void remove(T item);
    public ObservableList<T> getObservableList();
}
