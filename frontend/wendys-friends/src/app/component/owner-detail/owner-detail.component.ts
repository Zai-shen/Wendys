import {Component, Input, OnInit} from '@angular/core';
import {Owner} from '../../dto/owner';
import {OwnerService} from '../../service/owner.service';

@Component({
  selector: 'app-owner-detail',
  templateUrl: './owner-detail.component.html',
  styleUrls: ['./owner-detail.component.scss']
})
export class OwnerDetailComponent implements OnInit {

  @Input() detailOwner: Owner;

  constructor(private ownerService: OwnerService) {
  }

  ngOnInit() {
  }

  /**
   * Prepares the owner to update to fit rules
   * @param id of owner to update
   * @param owner params to update
   */
  onClickPutOwner(id:number, owner: Owner) :void{
    owner.id = null;
    owner.updatedAt = null;
    owner.createdAt = null;

    this.putOwner(id,owner);
    this.detailOwner = null;
  }

  // US-7
  /**
   * Updates specific owner from the backend
   * @param id of owner to update
   * @param owner params to update
   */
  private putOwner(id: number, owner: Owner){
    this.ownerService.putOwner(id,owner).subscribe(
      data => console.log('Success putting!', data),
      error => {
        console.log(error);
      }
    );
  }


}
