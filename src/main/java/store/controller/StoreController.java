package store.controller;

import store.model.membership.MemberShip;
import store.model.shopping.ShoppingCart;
import store.model.store.Counter;
import store.model.store.Store;
import store.model.promotion.PromotionManager;
import store.service.StoreService;
import store.util.Validator;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {
    private final StoreService storeService = new StoreService();
    private Store store;
    private Counter counter;
    private ShoppingCart shoppingCart;
    private MemberShip memberShip;

    public void run() {
        process(this::setupStore);
        process(this::displayMenu);
        process(this::handlePurchase);
        process(this::handleMembership);
        process(this::printReceipt);
    }

    private void setupStore() {
        PromotionManager manager = storeService.readPromotionFile();
        store = storeService.readProductsFile(manager);
    }

    private void displayMenu() {
        OutputView.printMenu(storeService.createStoreDTO(store));
    }

    private void handlePurchase() {
        String purchases = InputView.getPurchaseList();
        shoppingCart = storeService.createShoppingCart(purchases);
        counter = new Counter(store, shoppingCart);
        counter.purchaseProducts();
    }

    private void handleMembership() {
        String membershipConsent = InputView.getIsMembership();
        Validator.validateYesOrNo(membershipConsent);
        memberShip = storeService.createMemberShip(membershipConsent, counter);
    }

    private void printReceipt() {
        storeService.processPurchaseAndPrintReceipt(store, shoppingCart, counter, memberShip);
    }

    private void process(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            process(action);
        }
    }
}