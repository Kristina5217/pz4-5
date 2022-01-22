package com.metanit;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        Queue q = new Queue(10);
        q.Put(1);
        q.Put(30);
        q.Put(15);
        q.Put(78);
        q.Put(20);
        int[] arr = q.GetAll();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println();
        q.InsertingSort();
        arr = q.GetAll();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println();
        try {
            System.out.println(q.Get());
            System.out.println(q.Get());
            System.out.println(q.Get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        arr = q.GetAll();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

class Task
{
    public int[] array;

    Task(int size)
    {
        array = new int[size];
        FillArray();
    }

    public void CountOdd()
    {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0)
                count++;
        }
        System.out.println("Число четных элементов массива " + count);
    }

    public void CountEven()
    {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0)
                count++;
        }
        System.out.println("Число нечетных элементов массива " + count);
    }

    public void ShowArray()
    {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void FillArray()
    {
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(0, 11);
        }
    }
}

class Queue
{
    private int[] _array;
    private int _startIndex = 0;
    private int _endIndex = 0;

    Queue(int size)
    {
        if (size > 0)
            _array = new int[size];
        else
            _array = new int[5];
    }

    public void InsertingSort()
    {
        for (int i = _startIndex == 0 ? 0 : _startIndex - 1; i < _endIndex - 1; i++)
        {
            while (_array[i + 1] < _array[i] && i >= 0)
            {
                Swap(i, i + 1);
                if (i == 0)
                    break;
                i--;
            }
        }
    }

    public int Get() throws Exception
    {
        if(_startIndex == 5)
        {
            _startIndex = 0;
            _endIndex -= 5;
            ReduceArray();
        }
        _startIndex++;
        if(_endIndex < _startIndex)
        {
            _startIndex--;
            throw new Exception("Элементов в очереди нет");
        }
        return _array[_startIndex - 1];
    }

    public int[] GetAll()
    {
        int[] result = new int[_endIndex - _startIndex > 0 ? _endIndex - _startIndex : 0];
        for (int i = _startIndex, j = 0; i < _endIndex; i++, j++) { result[j] = _array[i];}
        return result;
    }

    public void Put(int value)
    {
        _endIndex++;
        if (_array.length - 1 < _endIndex) { IncreaseArray();}
        _array[_endIndex - 1] = value;
    }

    private void IncreaseArray()
    {
        int[] temp = _array.clone();
        _array = new int[temp.length * 2];
        for (int i = 0; i < temp.length; i++)
        {
            _array[i] = temp[i];
        }
    }

    private void ReduceArray()
    {
        int[] temp = _array.clone();
        _array = new int[_endIndex];
        for (int i = 5, j = 0; i < 5 + _endIndex; i++, j++)
        {
            _array[j] = temp[i];
        }
    }

    private void Swap(int firstIndex, int secondIndex)
    {
        int temp = _array[secondIndex];
        _array[secondIndex] = _array[firstIndex];
        _array[firstIndex] = temp;
    }
}
