package Index;


import java.io.ByteArrayOutputStream;

import java.io.PrintStream;
import java.util.ArrayList;

import UserDao.*;
import javafx.stage.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Index extends Application{
	static hospitalDao hos = new hospitalDao();
	public static void main(String[] args) throws Exception {
		PrintStream out = System.out;
		hos.load();
		Application.launch(args);
		System.setOut(out);
		System.out.println("结束");
		hos.close();
	}
	
	
	//主界面
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane bd = new BorderPane();
		bd.setStyle("-fx-background-image: url("+"file:src/bg3.jpg"+")");
		VBox vb = new VBox(50);
		Label welcome = new Label("欢迎使用新冠患者入住信息管理系统");
		Button select = new Button("查询信息");
		Button insert = new Button("添加患者");
		Button delete = new Button("删除信息");
		Button update = new Button("修改信息");
		select.setMinSize(120, 60);
		insert.setMinSize(120, 60);
		delete.setMinSize(120, 60);
		update.setMinSize(120, 60);
		select.setFont(new Font(20));
		insert.setFont(new Font(20));
		delete.setFont(new Font(20));
		update.setFont(new Font(20));
		welcome.setMinSize(200, 100);
		welcome.setFont(new Font(40));
		welcome.setStyle("-fx-text-fill:White;");
		vb.getChildren().addAll(welcome,select,insert,delete,update);
		vb.setAlignment(Pos.CENTER);
		
		//查询
		select.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Select s1 = new Select();
				try {
					s1.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//插入
		insert.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Insert i1 = new Insert();
				try {
					i1.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//删除
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Delete d1 = new Delete();
				try {
					d1.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//更新
		update.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Update u1 = new Update();
				try {
					u1.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		stage.setTitle("新冠患者入住管理系统");
		bd.setCenter(vb);
		Scene scene = new Scene(bd,1200,848);
		stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/tb.jpg"));
		stage.show();
	}
	
	
	
	//重定向流
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
	
	
	//搜索模块弹窗
	public class Select extends Application{
		@Override
		public void start(Stage stage) throws Exception {
			// TODO Auto-generated method stub
			stage.setTitle("病人查询");
			TextArea text = new TextArea();
			text.setEditable(false);
			PrintStream ps = new ConsolePrint(text);
			System.setOut(ps);
			HBox hb = new HBox(20);
			VBox vb = new VBox(30);
			Button no = new Button("按编号查询");
			Button name = new Button("按姓名查询");
			Button all = new Button("查询所有病人");
			TextField tname = new TextField();
			TextField tno = new TextField();
			
			//全部查询
			all.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					try {
						text.setText("");
						hos.select();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
			//按编号
			no.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String str = tno.getText();
					text.setText("");
					try {
						hos.select(Integer.valueOf(str));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("未查询到此人!");
					}
				}
			});
			
			//按姓名
			name.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String str = tname.getText();
					text.setText("");
					try {
						hos.select(str);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			hb.getChildren().addAll(no,tno,name,tname);
			vb.getChildren().addAll(all,hb,text);
			hb.setAlignment(Pos.CENTER);
			vb.setAlignment(Pos.CENTER);
			Scene scene = new Scene(vb,800,400);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.getIcons().add(new Image("file:src/tb.jpg"));
			stage.show();
		}
	}
	
	
	//插入模块弹窗
	public class Insert extends Application {

		@Override
		public void start(Stage stage) throws Exception {
			// TODO Auto-generated method stub
			VBox vb = new VBox(15);
			HBox hb1 = new HBox(42);
			HBox hb2 = new HBox(40);
			HBox hb3 = new HBox(10);
			HBox hage = new HBox(10);
			HBox hname = new HBox(10);
			HBox hsex = new HBox(10);
			TextArea text = new TextArea();
			Label welcome = new Label("病人信息");
			Label space = new Label();
			Label lname = new Label("姓名:");
			TextField tname = new TextField();
			Label bsex = new Label("性别:");
			RadioButton male = new RadioButton("男");
			RadioButton female = new RadioButton("女");
			ToggleGroup group = new ToggleGroup();
			Label lage = new Label("年龄:");
			TextField tage = new TextField();
			Label lino = new Label("病房:");
			Label ldoctor = new Label("主治医师:");
			TextField tdoctor = new TextField();
			Button addData = new Button("添加");
			
			int i;
			ArrayList<String> list = new ArrayList<>();
			list = hos.deptName();
			ObservableList<String> obList = FXCollections.observableArrayList(list);
			ComboBox<String> cb = new ComboBox<>(obList);
			cb.getSelectionModel().select(0);
			PrintStream ps = new ConsolePrint(text);
			System.setOut(ps);
			male.setToggleGroup(group);
			female.setToggleGroup(group);
			male.setSelected(true);
			addData.setFont(new Font(20));
			welcome.setFont(new Font(22));
			hname.getChildren().addAll(lname,tname);
			hage.getChildren().addAll(lage,tage);
			hsex.getChildren().addAll(bsex,male,female);
			hb1.getChildren().addAll(hname,hsex,space);
			hb2.getChildren().addAll(hage,lino,cb);
			hb3.getChildren().addAll(ldoctor,tdoctor);
			hb1.setAlignment(Pos.CENTER);
			hb2.setAlignment(Pos.CENTER);
			hb3.setAlignment(Pos.CENTER);
			vb.setAlignment(Pos.CENTER);
			
			addData.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					try {			
						String name,doctor;
						int age,sex,ino;
						name = tname.getText();
						doctor = tdoctor.getText();
						age = Integer.valueOf(tage.getText());
						if(((RadioButton) group.getSelectedToggle()).getText()=="男")
							sex = 1;
						else 
							sex = 2;
						ino = cb.getSelectionModel().getSelectedIndex()+1;
						hos.addData(name, sex, age, ino, doctor);
						text.setText("");
						System.out.println("添加成功!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						text.setText("");
						System.out.println("添加失败,请检查输入信息是否有误!");
					}
					
				}
			});
			
			
			
			
			vb.getChildren().addAll(welcome,hb1,hb2,hb3,addData,text);
			Scene scene = new Scene(vb,800,400);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("添加病人信息");
			stage.setScene(scene);
			stage.getIcons().add(new Image("file:src/tb.jpg"));
			stage.show();
		}
	}
	
	
	//删除模块弹窗
	
	public class Delete extends Application {

		@Override
		public void start(Stage stage) throws Exception {
			// TODO Auto-generated method stub
			Label lno = new Label("编号:");
			TextField tno = new TextField();
			Button no = new Button("查询");
			tno.setPromptText("请输入编号");
			TextArea text = new TextArea();
			Button sc = new Button("删除");
			PrintStream ps = new ConsolePrint(text);
			System.setOut(ps);
			text.setEditable(false);
			VBox vb = new VBox(40);
			HBox hb = new HBox(30);
			sc.setMinSize(80, 40);
			
			//按编号
			no.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String str = tno.getText();
					text.setText("");
					try {
						hos.select(Integer.valueOf(str));
						System.out.println("\n查询到以上患者,确认删除?");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("未查询到此人!");
					}
				}
			});
			
			sc.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					int pno = Integer.valueOf(tno.getText());
					try {
						hos.delData(pno);
						System.out.println("删除成功!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("请输入正确的编号!");
					}
				}
			});
			
			
			hb.getChildren().addAll(lno,tno,no);
			vb.getChildren().addAll(hb,sc,text);
			hb.setAlignment(Pos.CENTER);
			vb.setAlignment(Pos.CENTER);
			
			Scene scene = new Scene(vb,800,400);
			stage.setScene(scene);
			stage.setTitle("删除病人信息");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.getIcons().add(new Image("file:src/tb.jpg"));
			stage.show();
		}
		
	}
	
	
	//更新模块弹窗
	public class Update extends Application {

		@Override
		public void start(Stage stage) throws Exception {
			// TODO Auto-generated method stub		
			VBox vb = new VBox(15);
			HBox hb1 = new HBox(20);
			HBox hb2 = new HBox(65);
			HBox hb3 = new HBox(10);
			HBox hage = new HBox(10);
			HBox hname = new HBox(10);
			HBox hno = new HBox(10);
			HBox hsex = new HBox(10);
			TextArea text = new TextArea();
			Label space = new Label();
			Label welcome = new Label("更新信息");
			Label lno = new Label("编号:");
			TextField tno = new TextField();
			Label lname = new Label("姓名:");
			TextField tname = new TextField();
			Label bsex = new Label("性别:");
			RadioButton male = new RadioButton("男");
			RadioButton female = new RadioButton("女");
			ToggleGroup group = new ToggleGroup();
			Label lage = new Label("年龄:");
			TextField tage = new TextField();
			Label lino = new Label("病房:");
			Label ldoctor = new Label("主治医师:");
			TextField tdoctor = new TextField();
			Button addData = new Button("更新");
			int i;
			ArrayList<String> list = new ArrayList<>();
			list = hos.deptName();
			ObservableList<String> obList = FXCollections.observableArrayList(list);
			ComboBox<String> cb = new ComboBox<>(obList);
			cb.getSelectionModel().select(0);
			PrintStream ps = new ConsolePrint(text);
			System.setOut(ps);
			male.setToggleGroup(group);
			female.setToggleGroup(group);
			male.setSelected(true);
			addData.setFont(new Font(20));
			welcome.setFont(new Font(22));
			hname.getChildren().addAll(lname,tname);
			hage.getChildren().addAll(lage,tage);
			hsex.getChildren().addAll(bsex,male,female);
			hno.getChildren().addAll(lno,tno);
			hb1.getChildren().addAll(hno,hname);
			hb2.getChildren().addAll(hage,hsex,space);
			hb3.getChildren().addAll(ldoctor,tdoctor,lino,cb);
			hb1.setAlignment(Pos.CENTER);
			hb2.setAlignment(Pos.CENTER);
			hb3.setAlignment(Pos.CENTER);
			vb.setAlignment(Pos.CENTER);
			
			addData.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					try {			
						String name,doctor;
						int pno,age,sex,ino;
						name = tname.getText();
						doctor = tdoctor.getText();
						pno = Integer.valueOf(tno.getText());
						age = Integer.valueOf(tage.getText());
						if(((RadioButton) group.getSelectedToggle()).getText()=="男")
							sex = 1;
						else 
							sex = 2;
						ino = cb.getSelectionModel().getSelectedIndex()+1;
						System.out.println(pno+" "+name+" "+sex+" "+age+" "+ino+" "+doctor);
						hos.updateData(pno,name,sex,age,ino,doctor);
						
						text.setText("");
						System.out.println("更新成功!	");
						System.out.println("更新后患者信息:");
						hos.select(pno);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						text.setText("");
						System.out.println("更新失败,请检查输入信息是否有误!");
					}
					
				}
			});	
			vb.getChildren().addAll(welcome,hb1,hb2,hb3,addData,text);
			Scene scene = new Scene(vb,800,400);
			
			
			
			stage.setTitle("患者信息更新");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.getIcons().add(new Image("file:src/tb.jpg"));
			stage.setScene(scene);
			stage.show();
		}
		
		
	}
	
}
