public class TrafficLight extends Obserable
{
    // hier noch datenmodell hinzufügen. dein datenmodell ist zZ im Gui "gespeichert", jetzt müsstest du diese eigenschaften hier erstellen (etwa auto oder fussgängerampel, der aktuelle status etc) und vom gui aus die entsprechenden eigenschaften setzen.

    public void machWas()
    {
        notifyObservers();
    }
}

