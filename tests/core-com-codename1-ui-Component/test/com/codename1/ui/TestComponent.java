/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.ui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.maps.Coord;
import com.codename1.testing.AbstractTest;
import com.codename1.testing.TestUtils;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;

/**
 *
 * @author shannah
 */
public class TestComponent extends AbstractTest {

    @Override
    public boolean runTest() throws Exception {
        
        
        getComponentAt_int_int();
        List_shouldRenderSelection();
        return true;
    }

    @Override
    public boolean shouldExecuteOnEDT() {
        return true;
    }
    
    
    private void getComponentAt_int_int() {
        testNestedScrollingLabels();
        getComponentAt_int_int_button();
        getComponentAt_int_int_label();
        getComponentAt_int_int_container();
        getComponentAt_int_int_browsercomponent();
        
    }
    
    private void List_shouldRenderSelection() {
        com.codename1.ui.List l = new com.codename1.ui.List<>();
        
        // Make sure this doesn't throw a stack overflow error.
        try {
            l.shouldRenderSelection();
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException("Error in List.shouldRenderSelection");
        }
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
        TestUtils.waitForFormTitle("My Form", 2000);
        Component middleComponent = f.getComponentAt(w/2, h/2);
        assertEqual(l, middleComponent, "Found wrong component");
        
        
    }
    
    private void testNestedScrollingLabels() {
        int w = Display.getInstance().getDisplayWidth();
        int h = Display.getInstance().getDisplayHeight();
        Form f = new Form("Scrolling Labels");
        Toolbar tb = new Toolbar();
        f.setToolbar(tb);
        final Form backForm = Display.getInstance().getCurrent();
        tb.addCommandToSideMenu(new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (backForm != null) {
                    backForm.showBack();
                }
            }
        });
        f.setTitle("Scrolling Labels");
        Container cnt = new Container(BoxLayout.y());
        cnt.setScrollableY(true);
        for (String l : new String[]{"Red", "Green", "Blue", "Orange", "Indigo"}) {
            for (int i=0; i<20; i++) {
                cnt.add(l+i);
            }
        }
        
        f.setLayout(new BorderLayout());
        f.add(BorderLayout.CENTER, LayeredLayout.encloseIn(new Button("Press me"), BorderLayout.center(BorderLayout.center(cnt))));
        f.show();
        
        TestUtils.waitForFormTitle("Scrolling Labels", 2000);
        Component res = f.getComponentAt(w/2, h/2);
        assertTrue(res == cnt || res.getParent() == cnt, "getComponentAt(x,y) should return scrollable container on top of button when in layered pane.");
        
    }
    
    private void getComponentAt_int_int_container() {
        int w = Display.getInstance().getDisplayWidth();
        int h = Display.getInstance().getDisplayHeight();
        
        Form f = new Form("My Form", new BorderLayout());
        Container l = new Container();
        f.add(BorderLayout.CENTER, l);
        
        f.show();
        TestUtils.waitForFormTitle("My Form", 2000);
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
        
        TestUtils.waitForFormTitle("Maps", 2000);
        Component middleComponent = mapDemo.getComponentAt(w/2, h/2);
        assertTrue(mc == middleComponent || mc.contains(middleComponent),  "Wrong component found in middle. Expected "+mc+" but found "+middleComponent);
    
        tb.openSideMenu();
        TestUtils.waitFor(500); // wait for side menu to open
        
        Component res = null;
        
        res = tb.getComponentAt(10, h/2);
        
        //System.out.println("tb size = "+tb.getAbsoluteX()+", "+tb.getAbsoluteY()+", "+tb.getWidth()+", "+tb.getHeight());
        //System.out.println("mb size = "+tb.getMenuBar().getAbsoluteX()+", "+tb.getMenuBar().getAbsoluteY()+", "+tb.getMenuBar().getWidth()+", "+tb.getMenuBar().getHeight());
        //System.out.println("res is "+res);
        res = mapDemo.getComponentAt(10, h/2);
        
        // Let's find the interaction dialog on the form
        Component interactionDialog = $("*", mapDemo).filter(c->{
            return c instanceof InteractionDialog;
        }).asComponent();
        
        assertTrue(((InteractionDialog)interactionDialog).contains(res), "Toolbar is open so getComponentAt() should return something on the toolbar.  But received "+res+".  Toolbar is "+tb);
    
    }
    
    
    
}
