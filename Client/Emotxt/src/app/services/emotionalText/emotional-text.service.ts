import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmotionalTextService {

  RED = [255, 0, 0];
  GREEN = [0, 255, 0];
  BLUE = [0, 0, 255];
  YELLOW = [240, 255, 0];
  ORANGE = [255, 140, 0];
  GREY = [10, 10, 10];

  constructor() { }

  decimalToHex(num: number) {
    console.log('Entering decimalToHex with: ' + num);

    if (num < 0) {
      console.log('Invalid number entered decimalToHex');
      return 0;
    }
    if (num < 10) {
      return num;
    }
    switch (num) {
      case 10:
        return 'A';
      case 11:
        return 'B';
      case 12:
        return 'C';
      case 13:
        return 'D';
      case 14:
        return 'E';
      case 15:
        return 'F';
    }
  }

  rgbToHex(color: number[]) {
      if (color.length !== 3) {
        console.log('Invalid color');
        return null;
      }
      const hex = [];
      hex.push('#');
      color.forEach(channel => {
        hex.push(this.decimalToHex(Math.floor(channel / 16)));
        hex.push(this.decimalToHex(Math.floor(((channel % 16) % 1) * 16)));
      });
      let test: string = hex.join();
      test = test.replace(',', '');
      test = test.replace(',', '');
      test = test.replace(',', '');
      test = test.replace(',', '');
      test = test.replace(',', '');
      test = test.replace(',', '');
      console.log('Returning from rbgToHex with: ' + test);
      return test;
  }

  blendColors(col1: number[], col2: number[]) {
      if (col1.length !== 3 || col2.length !== 3) {
        console.log('Invalid color passed into blendColors');
        return null;
      }
      const newColor = [];

      for (let i = 0; i < 3; i++) {
        newColor.push((col1[i] + col2[i]));
      }
      console.log('Returning from blendColors with: ' + newColor);
      return newColor;
  }

  createColor(ratio: number, color: number[]) {
    if (color.length !== 3) {
      console.log('Invalid color passed into createColor');
      return null;
    }
    let newColor = [];
    color.forEach(channel => {
        newColor.push((channel * ratio));
    });
    console.log('Returning from createColor with: ' + newColor);
    return newColor;
  }

  convertUsingEmotion(emotionStr: string, text: string, body: HTMLParagraphElement): HTMLParagraphElement {

    // {"emotion":{"happy":0.951516,"sad":0.000051,"angry":0.000202,"fear":0.000434,
    // "excited":0.011763,"indifferent":0.036035}}
    console.log('Entering Emotion to Text');

    const emotional = JSON.parse(emotionStr);
    const emotion = emotional['emotion'];

    const happy = emotion['happy']; // Green
    const sad = emotion['sad']; // Blue
    const angry = emotion['angry']; // Red
    const fear = emotion['fear']; // Yellow
    const excited = emotion['excited']; // Orange
    const indifferent = emotion['indifferent']; // GREY

    let newRed = this.createColor(angry, this.RED);
    let newGreen = this.createColor(happy, this.GREEN);
    let newBlue = this.createColor(sad, this.BLUE);
    let newYellow = this.createColor(fear, this.YELLOW);
    let newOrange = this.createColor(excited, this.ORANGE);
    let newGrey = this.createColor(indifferent, this.GREY);

    let color = this.blendColors(newRed, newGreen);
    color = this.blendColors(color, newBlue);
    color = this.blendColors(color, newYellow);
    color = this.blendColors(color, newOrange);
    color = this.blendColors(color, newGrey);
    let hex = this.rgbToHex(color);
    body.style.color = hex.toString();
    return body;
  }
}
