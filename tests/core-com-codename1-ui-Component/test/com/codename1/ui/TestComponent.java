/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.ui;

import com.codename1.components.ToastBar;
import com.codename1.maps.Coord;
import com.codename1.testing.AbstractTest;
import com.codename1.testing.TestUtils;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;

/**
 *
 * @author shannah
 */
public class TestComponent extends AbstractTest {

    @Override
    public boolean runTest() throws Exception {
        
        
        getComponentAt_int_int();
        return true;
    }

    @Override
    public boolean shouldExecuteOnEDT() {
        return true;
    }
    
    
    private void getComponentAt_int_int() {
        getComponentAt_int_int_button();
        getComponentAt_int_int_label();
        getComponentAt_int_int_container();
        getComponentAt_int_int_browsercomponent();
    }
    
    
    
    
    private void getComponentAt_int_int_button() {
        log("Testing getComponentAt(x, y) with button");
        int w = Display.getInstance().getDisplayWidth();
        int h = Display.getInstance().getDisplayHeight();
        
        Form f = new Form("My Form", new BorderLayout());
        Label l = new Button("Hello");
        f.add(BorderLayout.CENTER, l);
        
        f.show();
        TestUtils.waitForFormTitle("My Form");
        Component middleComponent = f.getComponentAt(w/2, h/2);
        assertEqual(l, middleComponent, "Found wrong component");
    }
    
    private void getComponentAt_int_int_label() {
        log("Testing getComponentAt(x, y) with label");
        int w = Display.getInstance().getDisplayWidth();
        int h = Display.getInstance().getDisplayHeight();
        
        Form f = new Form("My Form", new BorderLayout());
        Label l = new Label("Hello");
        f.add(BorderLayout.CENTER, l);
        
        f.show();
        TestUtils.waitForFormTitle("My Form");
        Component middleComponent = f.getComponentAt(w/2, h/2);
        assertEqual(l, middleComponent, "Found wrong component");
        
        
    }
    
    
    
    
    private void getComponentAt_int_int_container() {
        int w = Display.getInstance().getDisplayWidth();
        int h = Display.getInstance().getDisplayHeight();
        
        Form f = new Form("My Form", new BorderLayout());
        Container l = new Container();
        f.add(BorderLayout.CENTER, l);
        
        f.show();
        TestUtils.waitForFormTitle("My Form");
        Component middleComponent = f.getComponentAt(w/2, h/2);
        assertEqual(l, middleComponent, "Found wrong component");
    }
    
    private void getComponentAt_int_int_browsercomponent() {
        int w = Display.getInstance().getDisplayWidth();
        int h = Display.getInstance().getDisplayHeight();
        Form mapDemo = new Form("Maps", new LayeredLayout());
        Toolbar.setOnTopSideMenu(true);
        Toolbar tb = new Toolbar();
        mapDemo.setToolbar(tb);
        mapDemo.setTitle("Maps");
        tb.addCommandToSideMenu(new Command("Test") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //testNestedScrollingLabels();
            }
        });
        BrowserComponent mc = new BrowserComponent();
        mapDemo.add(mc);
        
        mapDemo.show();
        
        TestUtils.waitForFormTitle("Maps");
        Component middleComponent = mapDemo.getComponentAt(w/2, h/2);
        assertTrue(mc == middleComponent || mc.contains(middleComponent),  "Wrong component found in middle. Expected "+mc+" but found "+middleComponent);
    
        tb.showToolbar();
        TestUtils.waitFor(500);
        Component res = mapDemo.getComponentAt(10, h/2);
        assertTrue(tb.contains(res), "Toolbar is open so getComponentAt() should return something on the toolbar.  But received "+res+".  Toolbar is "+tb);
    
    }
    
}
