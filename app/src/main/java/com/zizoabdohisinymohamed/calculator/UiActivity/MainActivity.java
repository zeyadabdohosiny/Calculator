package com.zizoabdohisinymohamed.calculator.UiActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zizoabdohisinymohamed.calculator.MathmaticalOperations.Mathmatical;
import com.zizoabdohisinymohamed.calculator.R;
import com.zizoabdohisinymohamed.calculator.RecycleView.Adapters.Operation_Adapter;
import com.zizoabdohisinymohamed.calculator.RecycleView.Modle.OperationModle;
import com.zizoabdohisinymohamed.calculator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    // Static Final Variablese
    private static final String TAG = "MainActicity";
    // Variables

    String lastOperation, currentOperation;
    int result,secondeOperande, numberChanging;

    // Lists && Stack

    Stack<String> undoLastOpe = new Stack<>();
    Stack<Integer> undoLastNumber = new Stack<>();
    Stack<String> redoLastOpe = new Stack<>();
    Stack<Integer> redoLastNumber = new Stack<>();
    ArrayList<OperationModle> list = new ArrayList<>();

    // Data Binding

    ActivityMainBinding binding;

    // Recycle View && Adapter
    private RecyclerView mRecyclerView;
    private Operation_Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data Binding

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // NumberChanging == The First Operand

        numberChanging = 0;
        secondeOperande = 0;
        currentOperation="";

        //RecycleView For A ListOfThePs
        mRecyclerView = findViewById(R.id.recycle_View_Operation);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new Operation_Adapter(list);
    }

    // Sum Listner

    public void sumListner(View view) {
        currentOperation = binding.additionButton.getText().toString();

    }


    // subtraction Listner

    public void subtractionListner(View view) {
        currentOperation = binding.subtractionButton.getText().toString();

    }

    //Multiplicatio Listner
    public void multiplicatioListner(View view) {
        currentOperation = binding.multiplicationButton.getText().toString();

    }

    // DivisionListner
    public void devisionListner(View view) {
        currentOperation = binding.divisionButton.getText().toString();

    }

    //Equal Listner
    public void equalListner(View view) {
        equal();

    }

    // Button When you want To Redo The Last Operation
    public void Redo(View view) {

        if (redoLastOpe.empty()) {
            // Simple Toast TO help The User

            Toast.makeText(this, "Some Thing Wrong", Toast.LENGTH_SHORT).show();

        } else {

            if (redoLastOpe.lastElement().equalsIgnoreCase("+") == true) {

                sum("Redo");

            } else if (redoLastOpe.lastElement().equalsIgnoreCase("-") == true) {

                sub("Redo");

            } else if (redoLastOpe.lastElement().equalsIgnoreCase("*") == true) {

                multiplication("Redo");


            } else if (redoLastOpe.lastElement().equalsIgnoreCase("/") == true) {

                divition("Redo");

            } else {

            }
            if (redoLastNumber.empty() && redoLastOpe.isEmpty()) {
                Toast.makeText(this, "Some Thing Wrong", Toast.LENGTH_SHORT).show();
            } else {
                redoLastOpe.pop();
                redoLastNumber.pop();
                equal();
                Log.d(TAG, "" + redoLastOpe.size());
            }


        }


    }

    // Button When you want To Return The Last Operation you do 2+2=4 , 4+2=6, Undo Result = 4
    public void Undo(View view) {
        if (undoLastOpe.empty()) {
            Toast.makeText(this, "Some Thing Wrong", Toast.LENGTH_SHORT).show();

        } else {


            if (undoLastOpe.lastElement().equalsIgnoreCase("+") == true) {
                sub("Undo");

            } else if (undoLastOpe.lastElement().equalsIgnoreCase("-") == true) {

                sum("Undo");

            } else if (undoLastOpe.lastElement().equalsIgnoreCase("*") == true) {
                divition("Undo");


            } else if (undoLastOpe.lastElement().equalsIgnoreCase("/") == true) {
                multiplication("Undo");

            } else {

            }
            if (undoLastNumber.empty() && lastOperation.isEmpty()) {
                Toast.makeText(this, "Some Thing Wrong", Toast.LENGTH_SHORT).show();
            } else {
                redoLastOpe.add(undoLastOpe.pop());
                redoLastNumber.add(undoLastNumber.pop());
                list.remove(list.size() - 1);
                equal();
            }


        }


    }

    // When you want To Sum 2 Number 2+2 =4
    public void sum(String s) {
        if (s.equalsIgnoreCase("Redo")) {
            secondeOperande = redoLastNumber.lastElement();
            numberChanging = Mathmatical.Sum(numberChanging, secondeOperande);
            undoLastOpe.add("+");
            undoLastNumber.add(secondeOperande);
            list.add(new OperationModle("+", "" + secondeOperande));
        }


        if (s.equalsIgnoreCase("Undo")) {
            secondeOperande = undoLastNumber.lastElement();
            numberChanging = Mathmatical.Sum(numberChanging, secondeOperande);


        } else {
            if (binding.EtSecondOperand.getText().toString().isEmpty() != true) {
                secondeOperande = Integer.parseInt(binding.EtSecondOperand.getText().toString());
                numberChanging = Mathmatical.Sum(numberChanging, secondeOperande);
                lastOperation = "+";
                undoLastOpe.add("+");
                undoLastNumber.add(secondeOperande);
                list.add(new OperationModle("+", "" + secondeOperande));
                Log.d(TAG, "" + list.size());

            } else {

                Toast.makeText(this, "Please enter A number", Toast.LENGTH_SHORT).show();
            }


        }


    }

    // When you want To Sun 2 Number 2-2 =0
    public void sub(String s) {
        if (s.equalsIgnoreCase("Redo")) {
            secondeOperande = redoLastNumber.lastElement();
            numberChanging = Mathmatical.Subtraction(numberChanging, secondeOperande);
            undoLastOpe.add("-");
            undoLastNumber.add(secondeOperande);
            list.add(new OperationModle("-", "" + secondeOperande));
        }


        if (s.equalsIgnoreCase("Undo")) {
            secondeOperande = undoLastNumber.lastElement();
            numberChanging = Mathmatical.Subtraction(numberChanging, secondeOperande);

        } else {

            if (binding.EtSecondOperand.getText().toString().isEmpty() != true) {
                secondeOperande = Integer.parseInt(binding.EtSecondOperand.getText().toString());
                numberChanging = Mathmatical.Subtraction(numberChanging, secondeOperande);
                lastOperation = "-";
                undoLastOpe.add("-");
                undoLastNumber.add(secondeOperande);
                list.add(new OperationModle("-", "" + secondeOperande));
            } else {
                Toast.makeText(this, "Please enter A number", Toast.LENGTH_SHORT).show();
            }
        }


    }

    // When you want To Multi 2 numbers 2*6 =12
    public void multiplication(String s) {
        if (s.equalsIgnoreCase("Redo")) {
            secondeOperande = redoLastNumber.lastElement();
            numberChanging = Mathmatical.multiplication(numberChanging, secondeOperande);
            undoLastOpe.add("*");
            undoLastNumber.add(secondeOperande);
            list.add(new OperationModle("*", "" + secondeOperande));
        }


        if (s.equalsIgnoreCase("Undo")) {
            secondeOperande = undoLastNumber.lastElement();
            numberChanging = Mathmatical.multiplication(numberChanging, secondeOperande);

        } else {
            if (binding.EtSecondOperand.getText().toString().isEmpty() != true) {
                secondeOperande = Integer.parseInt(binding.EtSecondOperand.getText().toString());
                numberChanging = Mathmatical.multiplication(numberChanging, secondeOperande);
                lastOperation = "*";
                undoLastOpe.add("*");
                undoLastNumber.add(secondeOperande);
                list.add(new OperationModle("*", "" + secondeOperande));
            } else {
                Toast.makeText(this, "Please enter A number", Toast.LENGTH_SHORT).show();
            }
        }

    }


    // When you want To Devision 2 Numbers 12 / 3 = 4
    public void divition(String s) {
        if (s.equalsIgnoreCase("Redo")) {
            secondeOperande = redoLastNumber.lastElement();
            numberChanging = Mathmatical.division(numberChanging, secondeOperande);
            undoLastOpe.add("/");
            undoLastNumber.add(secondeOperande);
            list.add(new OperationModle("/", "" + secondeOperande));
        }
        if (s.equalsIgnoreCase("Undo")) {
            secondeOperande = undoLastNumber.lastElement();
            numberChanging = Mathmatical.division(numberChanging, secondeOperande);


        } else {
            if (binding.EtSecondOperand.getText().toString().isEmpty() != true) {
                secondeOperande = Integer.parseInt(binding.EtSecondOperand.getText().toString());
                numberChanging = Mathmatical.division(numberChanging, secondeOperande);
                lastOperation = "/";
                undoLastOpe.add("/");
                undoLastNumber.add(secondeOperande);
                list.add(new OperationModle("/", "" + secondeOperande));
            } else {
                Toast.makeText(this, "Please enter A number", Toast.LENGTH_SHORT).show();
            }
        }


    }

   // Equal Method / This method Show to the User the Last result
    public void equal() {
        if(binding.EtSecondOperand.getText().toString().isEmpty()){
            Toast.makeText(this, "Some thing wrong", Toast.LENGTH_SHORT).show();
        }
        if (currentOperation.equalsIgnoreCase("+")) {
            sum("Equal");
        } else if (currentOperation.equalsIgnoreCase("-")) {
            sub("Equal");

        } else if (currentOperation.equalsIgnoreCase("*")) {
            multiplication("Equal");

        } else if (currentOperation.equalsIgnoreCase("/")) {
            divition("Equal");
        } else {
            Toast.makeText(this, "Please Choose Your Operation First", Toast.LENGTH_SHORT).show();
        }


        result = numberChanging;
        numberChanging = result;
        binding.TvResult.setText("Result = " + result);
        binding.EtSecondOperand.setText("");
        currentOperation = "";
        mRecyclerView.setAdapter(mAdapter);


    }


}
