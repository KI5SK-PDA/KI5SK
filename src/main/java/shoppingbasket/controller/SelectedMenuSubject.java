package shoppingbasket.controller;

public interface SelectedMenuSubject {
    void registerObserver(SelectedMenuObserver o);
    void removeObserver(SelectedMenuObserver o);
    void notifyObserver();
}
