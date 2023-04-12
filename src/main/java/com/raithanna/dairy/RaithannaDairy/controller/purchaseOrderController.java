package com.raithanna.dairy.RaithannaDairy.controller;
import com.raithanna.dairy.RaithannaDairy.models.purchaseOrder;
import com.raithanna.dairy.RaithannaDairy.models.supplier;
import com.raithanna.dairy.RaithannaDairy.repositories.CustomerRepository;
import com.raithanna.dairy.RaithannaDairy.repositories.PurchaseOrderRepository;
import com.raithanna.dairy.RaithannaDairy.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class purchaseOrderController {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @GetMapping("/purchase")
    public String purchaseOrderForm(Model model) {
        List<supplier> Suppliers = supplierRepository.findByOrderByIdDesc();
        System.out.println(Suppliers.size());
        purchaseOrder po = new purchaseOrder();
        model.addAttribute("purchase", po);
        model.addAttribute("supplier", Suppliers);
        return "purchase";
    }
    @PostMapping("/purchase")
    public ResponseEntity<?> savePurchases(@RequestParam Map<String, String> body, Model model) {
        System.out.println("1111111111111111111111");
        System.out.println(body);
        purchaseOrder po=new purchaseOrder();
        po.setSupplier(body.get("supplierName"));
        po.setRecDate(LocalDate.parse(body.get("recDate")));
        po.setSnfP(Double.parseDouble(body.get("snfP")));
        po.setFatP(Double.parseDouble(body.get("fatP")));
        po.setTsRate(Double.parseDouble(body.get("tsRate")));
        po.setLtrRate(Double.parseDouble(body.get("ltrRate")));
        po.setQuantity(Double.parseDouble(body.get("quantity")));
        po.setMilkType(body.get("milkType"));
        purchaseOrderRepository.save(po);
        System.out.println(body.get("supplierName"));
        System.out.println(body.get("recDate"));
        Map<String,String> respBody = new HashMap<>();
        return ResponseEntity.ok(respBody);

    }
//    @PostMapping(value = "/purchaseOrderFormNew")
//    public ModelAndView purchaseOrderFormNew(Model model, @RequestBody List<purchaseOrder> orderList) {
//        System.out.println("log info -- dailySalesList " + orderList.size());
//        Iterable<purchaseOrder> PurchaseIterable = purchaseOrderRepository.findAll();
//        List<purchaseOrder> PurchaseOrders = new ArrayList<>();
//        for (purchaseOrder PurchaseOrder : PurchaseIterable) {
//            PurchaseOrders.add(PurchaseOrder);
//            for (purchaseOrder list : orderList) {
//                purchaseOrder po = new purchaseOrder();
//                po.setSupplier(list.getSupplier());
//                po.setRecDate(list.getRecDate());
//                purchaseOrderRepository.save(po);
//            }
//        }
//        return new ModelAndView("/purchase");
//    }
}
