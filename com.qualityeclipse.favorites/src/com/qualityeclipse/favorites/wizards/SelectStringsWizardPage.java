package com.qualityeclipse.favorites.wizards;

import java.awt.Checkbox;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.*;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import com.qualityeclipse.favorites.wizards.Utils.Constants;

/**
 * The second page of the wizard contains a checkbox list of key/value pairs
 * that can be extracted from the source file. Rather than initializing its
 * contents when first created, this page updates its contents whenever it
 * becomes visible by overriding the setVisible() method.
 */
public class SelectStringsWizardPage extends WizardPage
{
   public static CheckboxTableViewer checkboxTableViewer;
   private IPath sourceLocation;
   private ExtractedStringsModel stringModel;
   static ExtractStringsWizard sswp = new ExtractStringsWizard();
   Display display = Display.getCurrent();
   Color red = display.getSystemColor(SWT.COLOR_RED);
   Color cc = new Color(Display.getCurrent(),145,193,230);
   Color cc1 = new Color(Display.getCurrent(),230,176,145);
   public Button yes,no;
   public Text newFile;
   
   public SelectStringsWizardPage() {
      super("selectStrings");
      setTitle("Export My Data");
      setDescription("Select the table fields to be exported");
   }

   /**
    * Creates the top level control for this dialog page under the given parent
    * composite, then calls <code>setControl</code> so that the created control
    * can be accessed via <code>getControl</code>
    * 
    * @param parent
    *           the parent composite
    */
   public void createControl(Composite parent) {
      Composite container = new Composite(parent, SWT.NULL);
      container.setLayout(new FormLayout());
      setControl(container);

      checkboxTableViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER);
      checkboxTableViewer.setContentProvider(new ExtractedStringsContentProvider());
      checkboxTableViewer.setLabelProvider(new ExtractedStringsLabelProvider());
      final Table table = checkboxTableViewer.getTable();
      final FormData formData = new FormData();
      formData.bottom = new FormAttachment(90, 0);
      formData.right = new FormAttachment(100, 0);
      formData.top = new FormAttachment(0, 0);
      formData.left = new FormAttachment(0, 0);
      table.setLayoutData(formData);
      table.setHeaderVisible(true);	
      
      final TableColumn tableColumn = new TableColumn(table, SWT.NONE);
      tableColumn.setWidth(200);
      tableColumn.setText("Table");
      tableColumn.setImage(new Image(Display.getCurrent(),
      "D:/Twitter_influence/images/chk1.png"));

      
      final TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
      tableColumn_1.setWidth(250);
      tableColumn_1.setText("Fields");
      stringModel = new ExtractedStringsModel(sourceLocation);
      checkboxTableViewer.setInput(stringModel);//System.out.println("dfewf"+sswp.strarrlist);
      
      tableColumn.addListener(SWT.Selection, new Listener() {
    	    public void handleEvent(Event event) {
    	        // TODO Auto-generated method stub
    	        boolean checkBoxFlag = false;
    	        for (int i = 0; i < table.getItemCount(); i++) {
    	            if (table.getItems()[i].getChecked()) {
    	                checkBoxFlag = true;
    	            }
    	        }

    	        if (checkBoxFlag) {
    	            for (int m = 0; m < table.getItemCount(); m++) {
    	                table.getItems()[m].setChecked(false);
    	                tableColumn.setImage(new Image(Display.getCurrent(),
    	                        "D:/Twitter_influence/images/chk1.png"));

    	                table.deselectAll();

    	            }
    	        } else {
    	            for (int m = 0; m < table.getItemCount(); m++) {
    	                table.getItems()[m].setChecked(true);
    	                tableColumn.setImage(new Image(Display.getCurrent(),
    	                        "D:/Twitter_influence/images/chk.png"));

    	                table.selectAll();
    	            }
    	        }

    	    }
    	});
      
      final TableItem [] itums = checkboxTableViewer.getTable().getItems();
	      for (int i = 0; i < itums.length; ++i) {
	    	  if(i<6){
	    	  itums[i].setBackground(cc);
	    	  }else if(i<10){
	    		  itums[i].setBackground(cc1);
	    	  }else if(i<12){
	    		  itums[i].setBackground(cc);
	    	  }else if(i<15){
	    		  itums[i].setBackground(cc1);
	    	  }else if(i<18){
	    		  itums[i].setBackground(cc);
	    	  }else if(i<22){
	    		  itums[i].setBackground(cc1);
	    	  }else if(i<24){
	    		  itums[i].setBackground(cc);
	    	  }else if(i<26){
	    		  itums[i].setBackground(cc1);
	    	  }else if(i<28){
	    		  itums[i].setBackground(cc);
	    	  }else if(i<32){
	    		  itums[i].setBackground(cc1);
	    	  }else if(i<33){
	    		  itums[i].setBackground(cc);
	    	  }else if(i<39){
	    		  itums[i].setBackground(cc1);
	    	  }else if(i<41){
	    		  itums[i].setBackground(cc);
	    	  }else if(i<44){
	    		  itums[i].setBackground(cc1);
	    	  }else if(i<51){
	    		  itums[i].setBackground(cc);
	    	  }else if(i<52){
	    		  itums[i].setBackground(cc1);
	    	  }else if(i<54){
	    		  itums[i].setBackground(cc);
	    	  }
	      }
	      SelectionListener selectionListener = new SelectionAdapter () {
	          public void widgetSelected(SelectionEvent event) {
	             Button button = ((Button) event.widget);
	             if(button.getSelection()){
	            	 if(button.getText()=="TSV"){
	            		 Constants.SEPARATOR_FOR_FIELDS_IN_TSV_FILE = "\t";
	            		 Constants.csv_or_tsv = "tsv";
	            	 }else{
	            		 Constants.SEPARATOR_FOR_FIELDS_IN_TSV_FILE = ",";
	            		 Constants.csv_or_tsv = "csv";
	            	 }
	             }
	             //System.out.println(" selected = " + button.getSelection());
	          };
	       };
	       
	      Label dog = new Label(container, SWT.NONE);
	      dog.setText("Export as:");
	      FormData datta = new FormData();
	      datta.top = new FormAttachment(table,10);
	      datta.left = new FormAttachment(0,0);
	      dog.setLayoutData(datta);
	      yes = new Button(container, SWT.RADIO);
	      FormData formData1 = new FormData();
	      formData1.top = new FormAttachment(table,10);
	      formData1.left = new FormAttachment(15,0);
	      yes.setLayoutData(formData1);
	      yes.setText("CSV");
	      yes.addSelectionListener(selectionListener);
	      
	      no = new Button(container, SWT.RADIO);
	      FormData formData2 = new FormData();
	      formData2.top = new FormAttachment(table,10);
	      formData2.left = new FormAttachment(yes,40);
	      no.setLayoutData(formData2);
	      no.setText("TSV");
	      no.addSelectionListener(selectionListener);
	      
	      Label dogName = new Label(container, SWT.NONE);
	      dogName.setText("Valid Characters:");
	      newFile = new Text(container, SWT.BORDER | SWT.SINGLE);	
	      
	      FormData data = new FormData();
	      data.left = new FormAttachment(0,0);
		  data.right = new FormAttachment(newFile,-5);
		  data.top = new FormAttachment(yes,10);
		  data.bottom = new FormAttachment(100,0);
		  dogName.setLayoutData(data);
	      
		  FormData formData3 = new FormData();
	      formData3.bottom = new FormAttachment(100,0);
	      formData3.left = new FormAttachment(dogName,10);
	      formData3.right = new FormAttachment(60,0);
	      formData3.top = new FormAttachment(yes,10);
	      newFile.setLayoutData(formData3);
	      //Constants.allValidCharactersInGH_Descriptions_ForRegEx = newFile.getText();
   }
   public void setVisible(boolean visible) {
	      super.setVisible(visible);
	   }
}
