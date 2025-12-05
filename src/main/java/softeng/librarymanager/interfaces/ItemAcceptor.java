/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softeng.librarymanager.interfaces;

/**
 *
 * @author Jakub
 */
public interface ItemAcceptor<T> {
    public void add(T item);
    public boolean isValid(T item);
}
