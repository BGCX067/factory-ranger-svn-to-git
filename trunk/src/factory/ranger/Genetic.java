/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger;

import factory.ranger.model.Factory;
import factory.ranger.model.ListMachine;
import factory.ranger.model.Machine;
import factory.ranger.utility.Input;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Wibi
 */
public class Genetic {
    // Konstanta
    public static final int INTERVAL = 10;
    public static final int MINIMUM_DURATION = 10;
    public static final int NUMBER_ELEMENT = 8;
    public static final int POPULATION_SIZE = 40;
    public static final float FIT = 100/NUMBER_ELEMENT;
    
    // Variabel
    public String[] population;
    public String[] selected;
    public String[] crossedover;
    public String[] mutated;
    public ArrayList<Machine> machines;
    private Random rand;
    public int keuntungan;
    public int indLength;
    public int[] profit = new int[POPULATION_SIZE];
    public float[] ratio = new float[POPULATION_SIZE];
    public int[] dependency;
    public ArrayList<Integer> machMax;
    
    // Method
    public Genetic() {
        population = new String[POPULATION_SIZE];
        machines = ListMachine.getSingleton().getListMachine();
        dependency = new int[machines.size()];
        initDependency();
        machMax = new ArrayList<Integer>();
        rand = new Random();
    }
    
    public Genetic(int H, int numVendor, int mapWidth, int mapHeight) { //H = jumlah jam kerja pabrik
        int fetch = Math.round(((float)((mapWidth + mapHeight)*numVendor)) / 60);
        indLength = H - fetch;
        
        population = new String[POPULATION_SIZE];
        machines = ListMachine.getSingleton().getListMachine();
        dependency = new int[machines.size()];
        initDependency();
        
        rand = new Random();
    }
    
    public void initDependency() {
        dependency[0] = -1;
        for (int i = 1; i < machines.size(); i++) {
            Machine m = machines.get(i);
            dependency[i] = m.getDependensi();
        }
    }
    
    public int fitness(String s) {   //total waktu kurang dari total jam kerja pabrik
                                     //ada Mi dan Mj, i < j, dan Mi dependen Mj
        int sumTime = 0;
        int fit = 0;
        int dependen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            sumTime = sumTime + getDuration(s,i);
        }
        
        for (int i = 0; i < s.length(); i++){
            for(int j=0; j< i; j++){
                if(ListMachine.getSingleton().getDependensiMachine((int)(s.charAt(i))) != -1){
                    if(ListMachine.getSingleton().getDependensiMachine((int)(s.charAt(i)))==
                            (int)(s.charAt(j))){
                        dependen = 1;
                        fit = 1;
                    }
                }
            }
        }
        
        if (sumTime > Factory.getSingleton().getH())
            fit = 0;
        else if (sumTime < Factory.getSingleton().getH())
            fit = fit + 1;
        else if (dependen == 0)
            fit = 0;
        
        return fit;
        
    }
    
    public String[] fitness2 (String[] s){
        Integer sumProfit = 0;
        float ratio[] = new float[20];
        Integer[] profit = new Integer[20];
        String[] fitTemp = new String[s.length];
        
        for (int i = 0; i < s.length; i++){
            for(int j = 0; j < s[i].length();j++) {
                profit[i] = getMesin(s[i],j).getProduct().getPrice();
            }
            sumProfit = sumProfit +  profit[i];
            ratio[i] = profit[i]/sumProfit;
                if (ratio[i] >= FIT){
                    fitTemp[i] = s[i];
                }
        }
        
        return fitTemp;
    }
    
    public int fit(String s) {
        int prof = 0;
        ArrayList<Integer> machs = new ArrayList<Integer>();
        
        for (int i = 0; i < indLength; i++) {
            Machine m = getMesin(s, i);
            if (m.getDependensi() != -1) {
                if (!(machs.contains(m.getDependensi()))) {
                    return 0;
                }
            }
            if (!(machs.contains(m.getNumber()))) {
                machs.add(m.getNumber());
            }
            prof += m.getProduct().getPrice() * m.getP();
        }
        
        return prof;
    }
    
    public void selection() {
        int total = 0;
        
        for (int i = 0; i < profit.length; i++) {
            profit[i] = fit(population[i]);
            total += profit[i];
            
        }
        
        for (int i = 0; i < ratio.length; i++) {
            ratio[i] = (float)profit[i]/total;
            System.out.println(ratio[i]);
        }
        
        ArrayList<String> goods = new ArrayList<String>();
        ArrayList<Integer> bads = new ArrayList<Integer>();
        float minRatio = (1f/POPULATION_SIZE)/2;
        
        for (int i = 0; i < ratio.length; i++) {
            if (ratio[i] >= minRatio) {
                goods.add(population[i]);
            } else {
                bads.add(i);
            }
        }
        
        for (int i : bads) {
            population[i] = goods.get(rand.nextInt(goods.size()));
        }
    }
    
 /*   public String maxFitness (String[] s){
        Integer sumProfit = 0;
        float ratio[] = new float[20];
        Integer[] profit = new Integer[20];
        String[] fitTemp = new String[s.length];
        String fittest = "";
        
        for (int i = 0; i < s.length; i++){
            for(int j = 0; j < s[i].length();j++) {
                profit[i] = getMesin(s[i],j).getProduct().getPrice();
            }
            sumProfit = sumProfit +  profit[i];
            ratio[i] = profit[i]/sumProfit;
                if (ratio[i] >= FIT){
                    fitTemp[i] = s[i];
                }
        }
        
        for (int i=0;i<fitTemp.length;i++)
        
        return fittest;
    } */
    
    private String generateIndividual() {
        String ind = "";
        
        for (int i = 0; i < indLength; i++) {
            int num = rand.nextInt(machines.size() - 1) + 1;
            ind += machines.get(num).getNumber() + " ";
        }
        
        return ind.substring(0, ind.length() - 1);
    }
    
    public void generatePopulation() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population[i] = generateIndividual();
        }
    }
    public void displayPopulation() {
        for (String ind : population) {
            System.out.println(ind);
        }
    }
    
    public void selection(String[] s) {
        int j = 0;
        for (int i = 0; i < s.length; i++){
            population[j] = "";
            if (fitness(s[i]) >= 1)
            {
                population[j] = s[i];
                j++;
            }
        }
    }
    
    public void crossOver(int idx1, int idx2) {
        int crossIdx = rand.nextInt(indLength - 1) + 1;
        
        String[] split1 = population[idx1].split(" "),
                split2 = population[idx2].split(" ");
        
        String newStr1 = "", newStr2 = "";
        for (int i = 0; i < crossIdx; i++) {
            newStr1 += split1[i] + " ";
        }
        for (int i = crossIdx; i < indLength; i++) {
            newStr1 += split2[i] + " ";
        }
        newStr1 = newStr1.substring(0, newStr1.length() - 1);
        
        for (int i = 0; i < crossIdx; i++) {
            newStr2 += split2[i] + " ";
        }
        for (int i = crossIdx; i < indLength; i++) {
            newStr2 += split1[i] + " ";
        }
        newStr2 = newStr2.substring(0, newStr2.length() - 1);
        
        population[idx1] = newStr2;
        population[idx2] = newStr1;
    }
    
    public String mutation(String S1, String S2, int idx) {
        String split1 = S1.substring(0, idx-1),
               split2 = S1.substring(idx+2);
        
        S1 = split1 + S2 + split2;
        return S1;
    }
    
    public void mutation(int idx) {
        int mutate = rand.nextInt(10);
        if (mutate == 0) {
            return;
        }
        
        int mutIdx = 2*(rand.nextInt(indLength));
        String oldStart = population[idx].substring(0, mutIdx);
        String oldEnd;
        if (mutIdx == 2*(indLength - 1)) {
            oldEnd = "";
        } else {
            oldEnd = population[idx].substring(mutIdx + 2, population[idx].length());
        }
        String mid;
        int r = rand.nextInt(machines.size() - 1) + 1;
        mid = machines.get(r).getNumber() + " ";
        
        population[idx] = oldStart + mid + oldEnd;
   }
    
    private Machine getMesin(String s, int idx) {
        int machName = Integer.parseInt(s.split(" ")[idx]);
        
        return machines.get(machName);
    }
    
    private int getDuration(String s, int idx) {
        String elmt = s.split(" ")[idx];
        return Integer.parseInt(elmt.substring(0, elmt.length() - 1));
    }
    
    private boolean feasible(String s) {
        boolean isFit = false;
        
        
        
        return isFit;
        
    }
    
    public void process() {
        selection();
        for (int i = 0; i < profit.length; i++) {
            System.out.println(profit[i]);
        }
        System.out.println("SELECTION");
        displayPopulation();
        
        System.out.println("CROSSOVER");
        for (int i = 0; i < POPULATION_SIZE; i += 2) {
            crossOver(i, i+1);
        }
        displayPopulation();
        
        System.out.println("MUTATION");
        for (int i = 0; i < POPULATION_SIZE; i++) {
            mutation(i);
        }
        displayPopulation();
    }
    
    public void loopProcess() {
        int j = 0;
        float stopRatio = (1f/POPULATION_SIZE)*1.5f;
        
        while (true) {
            System.out.println("Proses ke - " + j);
            process();
            for (int i = 0; i < ratio.length; i++) {
                if (ratio[i] >= stopRatio) {
                    break;
                }
            }
            j++;
            if (j > 100) {
                break;
            }
        }
        int max = 0;
        for (int i = 1; i < profit.length; i++) {
            if (profit[i] > profit[max]) {
                max = i;
            }
        }
        System.out.println("Ini yang terbaik!!!!");
        System.out.println(population[max]);
        System.out.println(profit[max]);
        
        printIndividu(population[max]);
        
    }
    
    public ArrayList<Integer> getNumber(String s){
        ArrayList<Integer> mesin  = new ArrayList<Integer>();
        String[] temp = new String[s.length()/2+1];
        ArrayList<Integer> produk = new ArrayList<Integer>();
        ArrayList<Integer> muncul = new ArrayList<Integer>();
        
        temp = s.split("\\s");
        for(int i=0; i<temp.length; i++){
            if(!mesin.contains(Integer.parseInt(temp[i])))
                mesin.add(Integer.parseInt(temp[i]));
        }
        return mesin;
    }
        
    
    public void printIndividu (String s)
    {
        ArrayList<Machine> mesin  = new ArrayList<Machine>();
        String[] temp = new String[s.length()/2+1];
        ArrayList<Integer> produk = new ArrayList<Integer>();
        ArrayList<Integer> muncul = new ArrayList<Integer>();
        
        temp = s.split("\\s");
        for(int i=0; i<temp.length; i++){
            if(!mesin.contains(getMesin(s,i))){
                mesin.add(getMesin(s,i));
            }
        }
        
        /*for (int i=0; i<mesin.size();i++){
            for (int i=0; i<s.length();i++ ){
                meisn
            }
        }*/
        
        
        for (int i=0; i<mesin.size();i++){
            int hasil = 0;
            hasil = (mesin.get(i).getP())* getMesin(s,i).getProduct().getPrice();
            produk.add(hasil);
        }
        
        System.out.print("Mesin yang digunakan: ");
        for (int i=0; i<mesin.size()-1; i++){
            //machMax.add(mesin.get(i).getNumber()); 
            System.out.print(mesin.get(i).getNumber() + ", ");
        }
        System.out.println(mesin.get(mesin.size()-1).getNumber());
        System.out.println("");
        
    }
    
    public static void main(String[] args) {
        Input input = new Input("coba.txt");
//        
//        Genetic init = new Genetic();
//        Genetic select = new Genetic();
//        
//        //1. Init population
//        init.generatePopulation();
//        
//        //2. Evaluate current population
//        //3. Selection
//        init.selection(init.population);
//        //4. Crossover
//        init.crossOver(init.rand.nextInt(7), init.rand.nextInt(7));       
//        //5. Mutation
//        init.mutation(init.crossedover[init.rand.nextInt(init.crossedover.length)] , init.population[init.rand.nextInt(POPULATION_SIZE)], init.rand.nextInt(init.crossedover.length));
        
        Genetic gen = new Genetic(18, 5, 10, 10);
        gen.generatePopulation();
        gen.displayPopulation();
        gen.loopProcess();
//        for (int i = 0; i < gen.profit.length; i++) {
//            System.out.println(gen.profit[i]);
//        }
    }
}
