//	AS03.cpp
//	Created by: Arielle Riray

#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <map>
#include <list>
#include <iterator>
#include <bits/stdc++.h>
#include <vector>
#include<algorithm>
#include <stdio.h>
#include <cctype>

using namespace std;

vector<string> secondWord;
bool typeFlag = false;
bool setReverse = false;
bool setDistinct = false;
bool menuLoop = true;

map<string, vector<string>>  test(map<string, vector<string>>  insertDict) {

    string line;
    ifstream file("Data.CS.SFSU.txt");

    if (file.is_open()) {
        cout << "! Opening data file... ./Data.CS.SFSU.txt" << endl;
        cout << "! Loading data..." << endl;

        while (getline(file, line)) {

            vector<string> vector1;
            vector<string> vector2;
            istringstream ss(line);
            string token;

            while (getline(ss, token, '|')) {
                vector1.push_back(token);
            }

            for (int i = 1; i < vector1.size(); i++) {
                vector2.push_back(vector1[i]);
            }

            string tmp = vector1[0];

            insertDict.insert(make_pair(tmp, vector2));
        }
        file.close();
        cout << "! Loading completed..." << endl;
        cout << "! Closing data file... ./Data.CS.SFSU.txt" << endl;
        }
    else {
        cout << "!<>ERROR<!> ===> File could not be opened." << endl;
        cout << "<!>ERROR<!> ===> Provided file path: " << __FILE__ <<endl;
        cout << "<!>Enter the CORRECT data file path: ./Data.CS.SFSU.txt" << endl;
        menuLoop = false;
    }
    return insertDict;
}

map<string, vector<string>> uniqueDict(map<string, vector<string>> &dictionary) {
    map<string ,vector<string>> :: iterator it;
    for(it=dictionary.begin();it !=dictionary.end();++it)
      {
       sort(it->second.begin(), it->second.end());
       it->second.erase(std::unique(it->second.begin(), it->second.end()), it->second.end());
      }
      return dictionary;
}
map<string, vector<string>> reverseDict(map<string, vector<string>> &dictionary) {
 //cout << " test reverse true" << endl;
    map<string ,vector<string>> :: iterator it;
    for(it=dictionary.begin();it !=dictionary.end();++it)
      {
       reverse(it->second.begin(),it->second.end());
      }
      return dictionary;
}

map<string, vector<string>> checkIf(map<string, vector<string>> &dictionary, string word) {

    if(word == "distinct") {
            //cout << " test distinct " << endl;
        uniqueDict(dictionary);
        setDistinct = true;
        return dictionary;
    }
    else if (word == "reverse"){
       // cout << " test reverse before" << endl;
        setReverse = true;
        return dictionary;
    }
    return dictionary;
}

void printHelp() {
    cout << "\t|"<<endl;
    cout << "\t PARAMETER HOW-TO, please enter:\n";
    cout << "\t 1. A search key -then 2. An optional part of speech -then\n";
    cout << "\t 3. An optional 'distinct' -then 4. An optional 'reverse'"<<endl;
    cout << "\t|"<<endl;
        }

string convertCase(string input){
    int len = input.size();
    char b;
    for(int i = 0; i < len; i++){
        b = input[i];
        b = tolower(b);
        input[i] = b;
    }
    return input;
}

void SplitString(string s, vector<string> &v){

	string temp = "";
	for(int i=0;i<s.length();++i){
		if(s[i]==' '){
			v.push_back(temp);
			temp = "";
		}
		else{
			temp.push_back(s[i]);
		}
	}
	v.push_back(temp);
}

string toUpper(string &str) {
    string strCSC = "csc";
    size_t found = str.find(strCSC);
    if (found != string::npos){
        string r = str.substr(0, 3);
        transform(r.begin(), r.end(), r.begin(), ::toupper);
        str.erase (0,3);
        str = r + str;
        return str;
    }
    str[0] = toupper(str[0]);
    return str;
}

string reformat(string str){
    char colon = '-';
    string bracket = "] :";
    string deletethis = "-=>> ";
    int index = str.find(colon);
    if (index != std::string::npos) {
        //cout << "Character found at index:  " << index << endl;
        str.insert(index-1,bracket);
        //Inserts a bracket
        int ind = str.find(colon)+5;
        //Finds index to make capital letter
        str[ind] = toupper(str[ind]);
        //Make The definitions capital letters
        }
    size_t pos = str.find(deletethis);
    if (pos != std::string::npos)
    {
        str.erase(pos, deletethis.length());
    }

    return str;
}

void printShortHelp(){
    cout << "\t <NOT FOUND> To be considered for the next release. Thank you." << endl;
    cout << "\t|"<<endl;
    }

void printDictionary(vector<string> v, map<string, vector<string>> dictionary, vector<string> keyWords) {

    cout << "\t|"<<endl;
    string searchForWord = v[0];
    int count = 0;
        for (auto it_map= dictionary.cbegin(); it_map!= dictionary.cend(); ++it_map) {
            if (v[0] == it_map->first) {

            //cout << "how many" << endl;
                    if(setReverse){
                        sort(dictionary.at(v[0]).begin(), dictionary.at(v[0]).end());
                        reverseDict(dictionary);
                    }else {
                    sort(dictionary.at(v[0]).begin(), dictionary.at(v[0]).end());
                    }

                    if (!typeFlag) {
                        for(const std::string& element: dictionary[v[0]])
                        {
                            cout << "\t " << toUpper(searchForWord) << " [" << reformat(element) << endl;
                            count++;
                        }
                    }
                    else if (typeFlag) {
                        for (const std::string& element: dictionary[v[0]]) {
                        string str1 = element;
                        string str2 = v[1];
                        if ((str1.find(str2) != string::npos)){
                            std::cout << "\t " << toUpper(searchForWord) << " [" << reformat(element)<< endl;
                            count++;
                        }
                    }
                }
            }
        }
        if (count == 0) {
            printShortHelp();
            printHelp();
        } else {
        cout << "\t|"<<endl;
    }
}

void fillVector(){
    secondWord.push_back("verb");
    secondWord.push_back("noun");
    secondWord.push_back("adjective");
    secondWord.push_back("adverb");
    secondWord.push_back("reverse");
    secondWord.push_back("distinct");

}



bool checkType(vector<string> v, int numberOfWords) {

    string dis = "distinct";
    string rev = "reverse";
    switch(numberOfWords) {
      case 1:
        if ((find( secondWord.begin(),secondWord.end(),v.at(0)) == secondWord.end())) {
            cout << "\t|" << endl;
            printShortHelp();
            cout << "\t|" << endl;
            printHelp();
            return false;
        }
        break;
      case 2:
        if ((v[1] == rev) || (v[1] == dis)){
            typeFlag = false;
        } else {
            typeFlag = true;
        }
        if ((find( secondWord.begin(),secondWord.end(),v.at(1)) == secondWord.end())) {
            cout << "\t|\n\t<The entered " << numberOfWords << "nd parameter '"+ v[1] + "' is NOT a part of speech.>\n";
            cout << "\t<The entered "<< numberOfWords <<"nd parameter '" + v[1] +"' is NOT 'distinct'.>\n";
            cout << "\t<The entered "<< numberOfWords <<"nd parameter '" + v[1] + "' is NOT 'reverse'.>\n";
            cout << "\t<The entered "<< numberOfWords <<"nd parameter '" + v[1]+ "' was disregarded.>\n";
            cout << "\t<The "<< numberOfWords <<"nd parameter should be a part of speech or 'distinct' or 'reverse'.>\n\t|"<<endl;
            typeFlag = false;
            return false;
        }
      break;
      case 3:
        if ((v.at(2) == rev) || (v[2] == dis)){
            return true;
        }
        else  {
            cout << "\t|\n\t<The entered "<< numberOfWords <<"rd parameter '" + v[2] +"' is NOT 'distinct'.>\n";
            cout << "\t<The entered "<< numberOfWords <<"rd parameter '" + v[2] +"' is NOT 'reverse'.>\n";
            cout << "\t<The entered "<< numberOfWords <<"rd parameter '"  + v[2] + "' was disregarded.>\n";
            cout << "\t<The "<< numberOfWords <<"rd parameter should be 'distinct' or 'reverse'.>\n\t|"<<endl;
            return false;
        }
        break;
      case 4:
          if (v[3] != "reverse"){
            cout << " \t|\n\t<The entered "<< numberOfWords <<"th parameter '" + v[3] + "' is NOT 'reverse'.>\n";
            cout << "\t<The entered "<< numberOfWords <<"th parameter '" + v[3] + "' was disregarded.>\n";
            cout << "\t<The "<< numberOfWords <<"th parameter should be 'reverse'.>\n\t|"<<endl;
            return false;
          }
        break;
    default : {
        return false; }
    }
}

int main() {

    map<string, vector<string>> dictionary;
    map<string, vector<string>> dictionaryReset;
    vector<string> keyWords;

    dictionary = test(dictionary);
    copy(dictionary.begin(), dictionary.end(), inserter(dictionaryReset, dictionaryReset.end()));

    int searchNum = 1;
    fillVector();
    for (map<string, vector<string>>::iterator it = dictionary.begin(); it != dictionary.end(); it++) {
        keyWords.push_back(it->first);
    }

    if (menuLoop == true) {
        cout << endl;
        cout << "====== DICTIONARY 340 C++ =====" << endl;
        cout << "------ Keywords: 19" << endl;
        cout << "------ Definitions: 61" << endl;
        cout << endl;
    }

    while (menuLoop) {
        cout  << "Search: [" << searchNum << "]: ";
        string input;
        getline(cin, input);

        searchNum++;

        input = convertCase(input);
        if (input == "" || input == "!help") {
            printHelp();
            continue;
        }
        if (input == "!q") {
            cout << "-----Thank You-----";
            return 0;
        }

    vector<string> v;
    SplitString(input, v);
    int numberOfWords = v.size();

    bool checker = true;
    switch(numberOfWords) {
      case 0 :
          break;
      case 1 :
          typeFlag = false;
          printDictionary(v, dictionary, keyWords);
          break;
      case 2 :
            checker = checkType(v, numberOfWords);
            checkIf(dictionary, v[1]);
            printDictionary(v, dictionary, keyWords);
         break;
      case 3 :
          checkType(v, 2);
          checker = checkType(v, numberOfWords);
          checkIf(dictionary, v[1]);
          checkIf(dictionary, v[2]);
          printDictionary(v, dictionary, keyWords);
         break;
      case 4 :
          checkType(v, 2);
          checkType(v, 3);
          checker = checkType(v, numberOfWords);
          checkIf(dictionary, v[1]);
          checkIf(dictionary, v[2]);
          if (v[3] == "reverse") {
            setReverse = true; }
          printDictionary(v, dictionary, keyWords);
         break;
      default:
         printHelp();
        break;
   }

   if (setDistinct) {
        dictionary.clear();
        copy(dictionaryReset.begin(), dictionaryReset.end(), std::inserter(dictionary, dictionary.end()));
   }

   setDistinct = false;
   setReverse = false;
   typeFlag = false;
    }
    cout << endl;
    cout << endl;
}
