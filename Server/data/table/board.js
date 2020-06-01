module.exports = (sequelize, DataTypes) => {
    return sequelize.define('board', {

        board_id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false
        },
        type: {
            type: DataTypes.STRING(45),
            allowNull: false
        }
    },{
        timestamps: false,
        tableName: 'board'
    })
}