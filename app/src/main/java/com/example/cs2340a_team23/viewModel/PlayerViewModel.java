package com.example.cs2340a_team23.viewModel;

import androidx.lifecycle.ViewModel;
import com.example.cs2340a_team23.model.Player;


public class PlayerViewModel extends ViewModel {
    private Player player;
    public PlayerViewModel() {
        Player player = Player.getPlayer();
    }

}
