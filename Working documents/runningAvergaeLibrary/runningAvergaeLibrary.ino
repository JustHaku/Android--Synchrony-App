/// SETTINGS /// 


#include "RunningAverage.h" 
#include "RunningSlope.h" 

// sample rate of taking GSR readings in Hz 

int sampleRate = 20; 

int MpointAvg = 125; 

#define N 100 

// You should think about the sampleRate and N carefully. 

// For example if the sampleRate is 20 and N is 100, then we are storing the previous 

// 100 / 20 = 5 seconds worth of data. This is the time scale over which we will compute 

// the average and standard deviation, which are used for spike detection. 

RunningAverage myRA(MpointAvg); 


int AvgSamples = 0; 

int SlpSamples = 0; 

const int GSR=A0; 

int sensorRaw; 

int sensorFilteredNew; 

int sensorFiltered[N]; 

int samplePeriod = 1000 / sampleRate; 

int pressuePin = 13; // pin for relay 

float rawData = 0; 

float how_many_std_above_the_mean = 0; 

unsigned long startTime;
unsigned long initialTime;

void setup(void)  

{ 

  Serial.begin(9600); 

  myRA.clear(); // explicitly start clean 

  pinMode(pressuePin,OUTPUT);
  initialTime = millis();
} 

  

void loop(void)  

{ 

  //rawData = analogRead(GSR); 

  // add to the array 

  myRA.addValue(analogRead(GSR)); 

  

  // count the numbber of samples 

  AvgSamples++; 

  

//get running avcergae and running stan 

  float runningAvg = myRA.getAverage(); 

  

  addToArray(runningAvg); // adding filtered value to array which is running avergae... 

  //float runningStd = myRA.getStandardDeviation(); 

   

  hasPeak(); 

  

  if (AvgSamples == 5000) // 5000 

  { 

    AvgSamples = 0; 

    myRA.clear(); 

  } 

  

   

  delay(samplePeriod);   // 1000/20 = 50ms = 20 Hz 

} 

  

  

void addToArray(float x) { 

  // index 0 holds the most recent value 

  // index N-1 holds the oldest value 

  for (int i = N - 1; i >= 1; i--) { 

    sensorFiltered[i] = sensorFiltered[i-1]; 

  } 

  sensorFiltered[0] = x; 

} 


void hasPeak() { 

  // calculate the average 

  float avg = 0; 

  for (int i = 0; i < N; i++) { 

    avg += sensorFiltered[i]; 

  } 

  avg /= N; 

  

  // calculate the standard deviation 

  float std = 0; 

  for (int i = 0; i < N; i++) { 

    std += pow(sensorFiltered[i] - avg, 2); 

  } 

  std = sqrt(std / N); 

  

  //Serial.println(avg); 

   

  //char str_currentValue[15]; 

  char str_runningAvg[15]; 

  char str_runningStd[8]; 

  char str_howManyStd[8]; 

  
   how_many_std_above_the_mean = -1*(sensorFiltered[0] - avg); 

  //Serial.println(how_many_std_above_the_mean); 

  /* 4 is mininum width, 2 is precision; float value is copied onto str_runningSlope*/ 

  //dtostrf(sensorFiltered[0],6,2,str_currentValue); // no need to add current value since we are using running avergae... :)  

  dtostrf(avg,6,2,str_runningAvg); // the basic array 

  dtostrf(std,4,2,str_runningStd); 

  dtostrf(how_many_std_above_the_mean,4,2,str_howManyStd); 

   

  char text[40]; 

  sprintf(text,"%s,%s,%s",str_runningAvg,str_runningStd,str_howManyStd); 

  Serial.println(text); 

  
  // if the current value is more than a standard deviation above the mean, 

  // then that counts as a peak. also make sure that the current value is 

  // more than 25 above the mean, in case there is just some noise in a 

  // pretty flat signal 

   
//  Serial.println(std); 

  if (how_many_std_above_the_mean > std && how_many_std_above_the_mean > 2) { //asad
            unsigned long currentTime = millis();
            if(currentTime-initialTime>2000){
            //digitalWrite(pressuePin,LOW);
            //delay(5000);
            digitalWrite(pressuePin,HIGH);
            initialTime = millis();
           }

 } else { 

    digitalWrite(pressuePin,HIGH);

  } 

  

} 
