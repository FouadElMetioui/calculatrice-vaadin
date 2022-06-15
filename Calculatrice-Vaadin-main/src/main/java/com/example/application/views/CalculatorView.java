package com.example.application.views;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Calculatrice")
@Route(value = "calcul", layout = MainLayout.class)
@Tag("calculator-view")
@JsModule("./views/calculator-view.ts")
public class CalculatorView extends LitTemplate {
    @Id
    private Button chf0;
    @Id
    private Button chf1;
    @Id
    private Button chf2;
    @Id
    private Button chf3;
    @Id
    private Button chf4;
    @Id
    private Label ecran;
    @Id
    private HorizontalLayout extendedLayout;
    @Id
    private Checkbox extendedChk;
    @Id
    private Button inverse;
    @Id("chf7")
    private Button chf7;
    @Id("chf8")
    private Button chf8;
    @Id("chf9")
    private Button chf9;
    @Id("ce")
    private Button ce;
    @Id("chf5")
    private Button chf5;
    @Id("chf6")
    private Button chf6;
    @Id("pr")
    private Button pr;
    @Id("div")
    private Button div;
    @Id("egal")
    private Button egal;
    @Id("pls")
    private Button pls;
    @Id("mns")
    private Button mns;
    @Id("cos")
    private Button cos;
    @Id("sin")
    private Button sin;
    @Id("tan")
    private Button tan;

    Double nbr1 = 0.0 ;
    Double nbr2 = 0.0 ;
    Double result = 0.0;
    char operation = ' ';
    public CalculatorView() {
        List<Button> chiffres = List.of(chf0,chf1,chf2,chf3,chf4,chf5,chf6,chf7,chf8,chf9);

        for (int i = 0; i < chiffres.size(); i++)
            prepare(chiffres,i);
        extendedChk.addValueChangeListener(this::onCheckChanged);
        inverse.addClickListener(this::onInverse);
        egal.addClickListener(this::getResult);
        ce.addClickListener(this::OnClear);
        pr.addClickListener(this::Product);
        div.addClickListener(this::division);
        pls.addClickListener(this::addition);
        mns.addClickListener(this::subtraction);
        cos.addClickListener(this::Cos);
        sin.addClickListener(this::Sin);
        tan.addClickListener(this::Tan);

    }

    private void Tan(ClickEvent<Button> buttonClickEvent) {
        double degre = Double.parseDouble(ecran.getText());
        double radian = Math.toRadians(degre);
        result = Math.tan(radian);
        ecran.setText(result.toString());
    }

    private void Sin(ClickEvent<Button> buttonClickEvent) {
        double degre = Double.parseDouble(ecran.getText());
        double radian = Math.toRadians(degre);
        result = Math.sin(radian);
        ecran.setText(result.toString());
    }

    private void Cos(ClickEvent<Button> buttonClickEvent) {
        double degre = Double.parseDouble(ecran.getText());
        double radian = Math.toRadians(degre);
        result = Math.cos(radian);
        ecran.setText(result.toString());
    }


    private void subtraction(ClickEvent<Button> buttonClickEvent) {
        nbr1= Double.parseDouble(ecran.getText());
        getResu();
        ecran.setText("-");
        operation = '-' ;
    }

    private void addition(ClickEvent<Button> a) {
        nbr1= Double.parseDouble(ecran.getText());
        getResu();
        ecran.setText("+");
        operation = '+' ;
    }

    private void division(ClickEvent<Button> d) {
        nbr1= Double.parseDouble(ecran.getText());
        getResu();
        ecran.setText("/");
        operation = '/' ;
    }

    private void Product(ClickEvent<Button> p) {
        nbr1= Double.parseDouble(ecran.getText());
        getResu();
        ecran.setText("x");
        operation = 'x';
    }

    private void OnClear(ClickEvent<Button> c) {
        ecran.setText("0.0");
        result = 0.0 ;
        nbr1 = 0.0;
        nbr2 = 0.0;
        operation = ' ';
    }

    private void getResu(){
        String ecr = ecran.getText();
        nbr2 = Double.valueOf(ecr);
        switch (operation){
            case '+' :
                result=nbr1+nbr2;
                break;
            case '-' :
                result=nbr1-nbr2;
                break;
            case 'x' :
                result=nbr1*nbr2;
                break;
            case '/' :
                if(nbr2 == 0){
                    Notification.show("Math Error !");
                    break ;
                }
                result=nbr1/nbr2;
                break;
        }
    }

    private void getResult(ClickEvent<Button> r) {
        getResu();
        ecran.setText(String.valueOf(result));
        result = 0.0 ;
        nbr1 = 0.0;
        nbr2 = 0.0;
        operation = ' ';
    }

    private void onInverse(ClickEvent<Button> e) {
        String ecr = ecran.getText();
        if(ecr.equals("0.0") ||ecr.equals("0") )
            Notification.show("Math Error !");
        else {
            nbr1= Double.valueOf(ecran.getText());
            result= 1/nbr1;
           ecran.setText(String.valueOf(result));
        }
    }

    private void onCheckChanged(AbstractField.ComponentValueChangeEvent<Checkbox, Boolean> e) {
        extendedLayout.setVisible(e.getValue());
    }

    private void prepare(List<Button> chiffres, Integer i) {
        Button btn = chiffres.get(i);
        btn.setText(i.toString());
        btn.addClickListener(this::addChiffre);
    }

    private void addChiffre(ClickEvent<Button> e) {
        String number = ecran.getText();
        if(number.equals("0.0") || number.equals("+") || number.equals("-") ||
              number.equals("x") || number.equals("/") || number.contains(".") )
            ecran.setText(e.getSource().getText());
        else
            ecran.setText(number+e.getSource().getText());
    }

}
