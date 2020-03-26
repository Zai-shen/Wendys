import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

import {Horse} from '../../dto/horse';

@Component({
  selector: 'app-post-horse',
  templateUrl: './post-horse.component.html',
  styleUrls: ['./post-horse.component.scss']
})
export class PostHorseComponent implements OnInit {

  @Input() validBreeds: string[];
  toPostHorse: Horse = new Horse('', 1, null, 'paint', 'noimage');
  @Output() childEvent = new EventEmitter<Horse>();
  private base64textString: string;

  constructor() {
  }

  ngOnInit(): void {
  }

  onClickPostHorse(horse: Horse) {
    this.childEvent.emit(horse);
  }

  onUploadChange(eVent: any) {
    const file = eVent.target.files[0];

    if (file) {
      const reader = new FileReader();

      reader.onload = this.handleReaderLoaded.bind(this);
      reader.readAsBinaryString(file);
    }
  }

  handleReaderLoaded(readerEvent) {
    // const binaryString = readerEvt.target.result;
    // this.base64textString = btoa(binaryString);
    this.base64textString = ('data:image/png;base64,' + btoa(readerEvent.target.result));
    this.toPostHorse.imageURI = this.base64textString;
    console.log(this.base64textString.length>420?this.base64textString.substring(0,420)+'...etc':this.base64textString);
  }

}
