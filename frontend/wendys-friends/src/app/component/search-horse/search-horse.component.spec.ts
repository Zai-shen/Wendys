import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchHorseComponent } from './search-horse.component';

describe('SearchHorseComponent', () => {
  let component: SearchHorseComponent;
  let fixture: ComponentFixture<SearchHorseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchHorseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchHorseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
