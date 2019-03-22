const int breathing_sensor = A0; // sensor_pin
int breathing_raw_data = 0;
float pzt_percentage;


void setup() {
  // put your setup code here, to run once:
   pinMode(9, OUTPUT);
   Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  analogWrite(9,90.8);
  breathing_raw_data = analogRead(breathing_sensor);
  pzt_percentage = ((breathing_raw_data/1024) - (0.5));
  Serial.println(breathing_raw_data);
  delay(100);
  

}
