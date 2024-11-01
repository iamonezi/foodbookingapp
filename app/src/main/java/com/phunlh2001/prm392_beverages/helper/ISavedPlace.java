package com.phunlh2001.prm392_beverages.helper;

import com.phunlh2001.prm392_beverages.viewmodel.AddressViewModel;

public interface ISavedPlace {
    void onClickEditAddress(AddressViewModel address);

    void onClickDeleteAddress(AddressViewModel address);

    void onClickItemAddress(AddressViewModel address);
}
