import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application{
	
	public static void main(String[] args) {
		PrintStream out = System.out;
		launch(args);
		
		System.setOut(out);
		System.out.println("哈哈哈");
	}

	public class ConsolePrint extends PrintStream {
	        TextArea console;
	 
	        public ConsolePrint(TextArea console) {
	           super(new ByteArrayOutputStream());
	            this.console = console;
	        }
	 
	        @Override
	        public void write(byte[] buf, int off, int len) {
	            print(new String(buf, off, len));
	        }
	 
	        @Override
	        public void print(String s) {
	            console.appendText(s);
	        }
	    }



	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		TextArea text = new TextArea();
		PrintStream ps = new ConsolePrint(text);
		PrintStream out = System.out;
		System.setOut(ps);
		VBox pane = new VBox();
		
		Button bt = new Button("问候");
		pane.getChildren().addAll(bt,text);
		bt.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				// TODO Auto-generated method stub
				System.out.println("你好，世界~");
			}
		});
		
		
		
		Scene scene = new Scene(pane);
		
		

		stage.setScene(scene);
		stage.show();
		System.setOut(out);
	}
	
	@Override
	public void stop() throws Exception{
		super.stop();
		PrintStream out = System.out;
		System.setOut(out);
		System.out.println("程序结束");
		
	}

}
