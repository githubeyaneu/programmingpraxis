package eu.eyan.programmingpraxis.crackerbarrel.cbimpl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import eu.eyan.programmingpraxis.crackerbarrel.game.IStep;

@EqualsAndHashCode()
@AllArgsConstructor(staticName = "step")
@ToString(includeFieldNames = false)
public class Step implements IStep
{
    @Getter
    private int from;

    @Getter
    private Direction direction;
}