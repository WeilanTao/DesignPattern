package Composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * We will treat Neuron and NeuronLayer in a uniform fashion
 */

interface SomeNeurons extends  Iterable<Neuron>{
    default  void  connectTo(SomeNeurons neurons){
        if (this==neurons) return;

        for(Neuron from: this){
            for(Neuron to: neurons){
                from.out.add(to);
                to.in.add(from);
            }
        }
    };
}

class Neuron implements  SomeNeurons{
    public ArrayList<Neuron>in, out;


    @Override
    public Iterator<Neuron> iterator() {
        return Collections.singleton(this).iterator();
    }

    @Override
    public void forEach(Consumer<? super Neuron> action) {
        action.accept(this);
    }

    @Override
    public Spliterator<Neuron> spliterator() {
        return Collections.singleton(this).spliterator();
    }
}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeurons{

}

public class _2NeuralNetworks {
    public static void main(String[] args) {
        Neuron neuron =  new Neuron();
        Neuron neuron1 =  new Neuron();

        NeuronLayer layer = new NeuronLayer();
        NeuronLayer layer1 = new NeuronLayer();

        neuron.connectTo(neuron1);
        neuron.connectTo(layer);
        layer.connectTo(neuron);
        layer.connectTo(layer1);
    }
}
