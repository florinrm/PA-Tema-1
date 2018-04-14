#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

// Copyright 2018 Florin Mihalache

class Solve {
 public:
    int sum, positions, index;
    Solve(int sum, int positions, int index) {
        count = 0;
        this->sum = sum;
        this->positions = positions;
        this->index = index;
    }

    // construirea prin backtracking a numaratorii
    void findSol(int suma, int insert_pos, int acc, vector<int> numbers) {
        if (insert_pos < 2) {
            // daca termin de inserat trec la indicele urmator la numaratoare
            if (is_sorted(acc, numbers)) {
                ++count;
                numbers.push_back(sum - acc);  // adaug ultimul element
                if (count == this->index + 1) {
                /* daca am ajuns la indicele cautat afisez numaratoarea si dau 
                exit ca sa nu se goleasca vectorul si sa afiseze doar - */
                    printFileSuccess(numbers);
                }
            }
            return;  // daca vectorul nu e sortat ies din recursivitate
        }
        int element = suma - (this->positions - 1);
        /* element e elementul pe care il inserez in vectorul de numaratori 
        si ii atribui valoare de la inceputul inserarii */
        if (insert_pos != this->positions) {
            int elem2 = this->sum - acc - (insert_pos - 1);
            element = suma <= elem2 ? suma : elem2;
        }
        /* verific daca depasesc suma data din fisier si in caz contrar
        pun elementul in vector, apelez recursiv si scot elementul din vector
        pentru a face combinarile, apoi scad elementul pentru combinari */
        while (element > 0) {
            if ((acc + element) >= sum) {
                break;
            } else {
                numbers.push_back(element);
                findSol(element, insert_pos - 1, acc + element, numbers);
                numbers.pop_back();
            }
            --element;
        }
    }

    void printFileFail() {
        FILE *output = fopen("numaratoare.out", "w");
        fprintf(output, "-");
        fclose(output);
    }  // afisare in caz ca nu gasesc numaratoarea

 private:
    int count;

    /* vad daca vectorul de numaratoare este sortat ok descrescator conform
    criteriilor din enunt */
    bool is_sorted(int suma, vector<int> numbers) {
        return (numbers[this->positions - 2] >= this->sum - suma);
    }
    // afisarea numaratorii in caz ca am gasit la indexul cautat
    void printFileSuccess(vector<int> numbers) {
        FILE *output = fopen("numaratoare.out", "w");
        fprintf(output, "%d=", sum);
        for (int i = 0; i < (int)numbers.size(); ++i) {
            if (i == 0)
                fprintf(output, "%d", numbers[i]);
            else
                fprintf(output, "+%d", numbers[i]);
        }
        fclose(output);
        exit(0);  // dau exit ca sa ies de tot, caci altfel vectorul e golit
    }
};

int main() {
    int sum, positions, index;
    FILE *fin = fopen("numaratoare.in", "r");
    fscanf(fin, "%d%d%d", &sum, &positions, &index);
    fclose(fin);
    vector<int> numbers;  // vectorul cu numaratoarea
    // (las aici caci daca era in clasa nu se salvau ca lumea elementele)
    Solve task = Solve(sum, positions, index);
    task.findSol(sum, positions, 0, numbers);  // rezolvarea
    if (numbers.empty())
        task.printFileFail();
    return 0;
}

