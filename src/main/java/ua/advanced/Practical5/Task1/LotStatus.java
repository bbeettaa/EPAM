package ua.advanced.Practical5.Task1;

public enum LotStatus {
    Created("Created"),
    Started("Started"),
    WaitingForParticipants("Waiting for participants"),
    WaitingForBets("Waiting for bets"),
    PriceUpdated("Price update"),
    WinnerAbsent("Winner is absent"),
    Win("Participant win"),
    WaitingForPayment("Waiting for payment"),
    PaymentSuccessful("Payment successful"),
    PaymentFailed("Payment failed"),
    Closed("Closed");
    private String name;
    LotStatus(String name){
        this.name=name;
    }
}
