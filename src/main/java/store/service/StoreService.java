package store.service;

import static store.util.Constant.YES;

import java.util.List;
import java.util.Map;

import store.model.membership.MemberShip;
import store.model.promotion.PromotionManager;
import store.model.shopping.ReceiptDTO;
import store.model.shopping.ShoppingCart;
import store.model.store.Counter;
import store.model.store.Store;
import store.model.store.StoreDTO;
import store.util.FilesReader;
import store.util.Parser;
import store.util.Validator;
import store.view.OutputView;

public class StoreService {

    public PromotionManager readPromotionFile() {
        List<String> promotionFile = FilesReader.readPromotionFile();
        return new PromotionManager(promotionFile);
    }

    public Store readProductsFile(PromotionManager manager) {
        List<String> productsFile = FilesReader.readProductsFile();
        return new Store(productsFile, manager);
    }

    public ShoppingCart createShoppingCart(String purchases) {
        Map<String, String> purchaseList = Parser.parsedPurchase(purchases);
        return new ShoppingCart(purchaseList);
    }

    public MemberShip createMemberShip(String membershipConsent, Counter counter) {
        Validator.validateYesOrNo(membershipConsent);
        if (membershipConsent.equalsIgnoreCase(YES.getMessage())) {
            int nonePromotionMoney = counter.getNonePromotionMoney();
            return new MemberShip(nonePromotionMoney);
        }
        return new MemberShip(0);
    }

    public void processPurchaseAndPrintReceipt(
        Store store,
        ShoppingCart shoppingCart,
        Counter counter,
        MemberShip memberShip
    ) {
        ReceiptDTO receipt = createReceiptDTO(shoppingCart, store, counter, memberShip);
        OutputView.printReceipt(receipt);
    }

    public ReceiptDTO createReceiptDTO(ShoppingCart shoppingCart, Store store, Counter counter, MemberShip memberShip) {
        return ReceiptDTO.of(shoppingCart.getPurchases(), store.getBenefits(), counter, memberShip);
    }

    public StoreDTO createStoreDTO(Store store) {
        return StoreDTO.from(store);
    }
}